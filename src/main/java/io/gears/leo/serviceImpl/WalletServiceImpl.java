package io.gears.leo.serviceImpl;

import com.google.common.collect.Lists;
import io.gears.leo.dao.Wallet;
import io.gears.leo.exception.InsufficientBalanceException;
import io.gears.leo.exception.WalletException;
import io.gears.leo.repos.WalletRepositry;
import io.gears.leo.service.AccountService;
import io.gears.leo.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Omar Essa
 * This Implementation of Wallet Service interface
 */

@Service
@Transactional
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepositry walletRepository;

    @Autowired
    private AccountService accountService;


    @Override
    public List<Wallet> transactionsByAccountID(Long accountId) throws WalletException {
        return walletRepository.getTransactionsForPlayer(accountId);
    }

    /**
     * retrieve transactions by their transaction reference this operations is
     * used to validate if a transaction ref has been used previously
     */

    @Override
    public Wallet transactionByRef(Long txnRef) throws WalletException {
        return walletRepository.getTransactionByRef(txnRef).
                orElseThrow(() -> new WalletException(String.format("transaction with ref '%d' doesnt exist", txnRef)));

    }

    @Override
    public BigDecimal balanceByAccountID(Long accountId) throws WalletException {
        return walletRepository.getBalance(accountId);
    }

    @Override
    public List<Wallet> transactions() {
        return Lists.newArrayList(walletRepository.findAll());
    }


    /**
     * this operations registers a transaction on behalf of player
     * debit/credits, it also validates if a player has insufficient funds if a
     * debit is to be made
     */

    @Override
    @Transactional
    public synchronized Wallet createTransaction(Wallet wallet) throws WalletException, InsufficientBalanceException {
        if (walletRepository.getTransactionByRef(wallet.getTransactionReference()).isPresent()) {
            throw new WalletException("transaction ref has been used ");
        }
        BigDecimal balance = walletRepository.getBalance(wallet.getAccount().getId());

        if (balance.add(wallet.getAmount()).compareTo(BigDecimal.ZERO) == 1
                | balance.add(wallet.getAmount()).compareTo(BigDecimal.ZERO) == 0) {
            return walletRepository.save(wallet);
        }

        throw new InsufficientBalanceException(String.format("player's balance is %.2f and cannot perform a transaction of %.2f ",
                balance.doubleValue(), wallet.getAmount().doubleValue()));
    }

    @Override
    public Wallet save(Wallet wallet) throws WalletException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Wallet update(Wallet wallet, Long id) throws WalletException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Wallet> getList() {
        return Lists.newArrayList(walletRepository.findAll());
    }
}
