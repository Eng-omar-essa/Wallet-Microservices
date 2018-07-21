package io.gears.leo.serviceImpl;

import com.google.common.collect.Lists;
import io.gears.leo.dao.Account;
import io.gears.leo.exception.WalletException;
import io.gears.leo.repos.AccountRepositry;
import io.gears.leo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Omar Essa
 */

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepositry accountRepository;


    @Override
    public Optional<Account> accountByPK(Long accountId) throws WalletException {
        return accountRepository.findById(accountId);
    }

    @Override
    @Transactional
    public Account save(Account account) throws WalletException {
        return accountRepository.save(account);

    }

    @Override
    @Transactional
    public Account update(Account account, Long id) throws WalletException {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getList() {
        return Lists.newArrayList(accountRepository.findAll());
    }
}
