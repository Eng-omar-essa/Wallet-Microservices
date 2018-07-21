package io.gears.leo.service;

import io.gears.leo.dao.Wallet;
import io.gears.leo.exception.InsufficientBalanceException;
import io.gears.leo.exception.WalletException;

import java.math.BigDecimal;
import java.util.List;


public interface WalletService extends PlayerService<Wallet> {

    List<Wallet> transactionsByAccountID(Long accountId) throws WalletException;

    Wallet transactionByRef(Long txnRef) throws WalletException;

    BigDecimal balanceByAccountID(Long accountId) throws WalletException;

    List<Wallet> transactions();

    Wallet createTransaction(Wallet wallet) throws WalletException, InsufficientBalanceException;
}
