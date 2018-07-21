package io.gears.leo.repos;

import io.gears.leo.dao.Account;
import io.gears.leo.exception.WalletException;
import io.gears.leo.service.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositryTest {

    @Autowired
    private AccountService accountService;

    /**
     * quickly setup a test account to do
     * out test with
     */
    @Before
    public  void setupAccount() throws WalletException {
        accountService.save(new Account(1L, "james doe", "M", new Date()));
    }

    @Test
    public void findPlayerByValidPrimaryKey() throws WalletException {
        Optional<Account> account = accountService.accountByPK(1L);
        if(account.isPresent()){
          Account  account_ = account.get();
            assertNotNull(account);
            Assert.assertEquals("james doe", account_.getPlayerName());
        }

    }

    @Test(expected = WalletException.class)
    public void createPlayerWithEmptyPlayerName() throws WalletException {
        accountService.save(new Account(1L, "", "M", new Date()));
    }

    @Test(expected = WalletException.class)
    public void createPlayerWithPlayerNameLessThan5Characters() throws WalletException {
        accountService.save(new Account(1L, "", "M", new Date()));
    }

    @Test(expected = WalletException.class)
    public void createPlayerWithNullPlayerName() throws WalletException {
        accountService.save(new Account(1L, null, "M", new Date()));
    }

    @Test
    public void createPlayerWithValidDetails() throws WalletException {
        accountService.save(new Account(2L, "gumball darwin", "M", new Date()));
    }
}