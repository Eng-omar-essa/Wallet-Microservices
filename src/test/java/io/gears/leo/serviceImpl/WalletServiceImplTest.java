package io.gears.leo.serviceImpl;

import io.gears.leo.dao.Account;
import io.gears.leo.dao.Wallet;
import io.gears.leo.exception.InsufficientBalanceException;
import io.gears.leo.exception.WalletException;
import io.gears.leo.service.AccountService;
import io.gears.leo.service.WalletService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.Assert.*;

/**
 *
 * @author Omar Essa
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class WalletServiceImplTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private WalletService walletService;

    private Account account;

    @Before
    public void setupPlayerAccount() throws WalletException {
        account = accountService.save(new Account(1L,"Jon Doe", "M", new Date()));
    }

    @Test(expected = InsufficientBalanceException.class)
    public void registerDebitTransactionof5000() throws WalletException, InsufficientBalanceException {
        Wallet wallet = new Wallet(account, new BigDecimal(-5000), "debit", new Date(), 150L);
        walletService.createTransaction(wallet);
    }

    @Test
    public void registerCreditTransactionof10000() throws WalletException, InsufficientBalanceException {
        Wallet saved1 = new Wallet(account, new BigDecimal(10000), "credit", new Date(), 150L);
        Wallet savedTransaction1 = walletService.createTransaction(saved1);
        assertNotNull(savedTransaction1);
        BigDecimal balance = walletService.balanceByAccountID(account.getId());
        assertTrue(balance.doubleValue() == 10000);
    }

    @Test
    public void triggerInconsistency() throws InterruptedException, InsufficientBalanceException, WalletException {

        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(2);
        Function<Wallet, Runnable> txRunner = w -> () -> {
            try {
                walletService.createTransaction(w);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                latch.countDown();
            }
        };

        Wallet tx0 = new Wallet(account, BigDecimal.valueOf(100), "", new Date(), 99L);
        Wallet tx1 = new Wallet(account, BigDecimal.valueOf(-60), "", new Date(), 100L);
        Wallet tx2 = new Wallet(account, BigDecimal.valueOf(-60), "", new Date(), 101L);

        walletService.createTransaction(tx0);


        executor.submit(txRunner.apply(tx1));
        executor.submit(txRunner.apply(tx1));
        executor.submit(txRunner.apply(tx2));

        latch.await(10, TimeUnit.SECONDS);

        System.out.println("Balance is: "+walletService.balanceByAccountID(account.getId()));
        // Balance might be -20.
    }
}