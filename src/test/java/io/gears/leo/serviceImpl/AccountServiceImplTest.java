package io.gears.leo.serviceImpl;

import io.gears.leo.dao.Account;
import io.gears.leo.exception.WalletException;
import io.gears.leo.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
/**
 *
 * @author Omar Essa
 */
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class AccountServiceImplTest {

    @Mock
    AccountService accountService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void accountByPK() throws WalletException {
        Account account = new Account(1L, "james doe", "M", new Date());
        when(accountService.accountByPK(account.getId())).thenReturn(Optional.ofNullable(account));
        assertNotNull(account);

    }

    @Test
    public void save() throws WalletException {
        Account account = new Account(1L, "james doe", "M", new Date());
        when(accountService.save(account)).thenReturn(account);
        assertNotNull(account);
    }

    @Test
    public void update() throws WalletException {
        Account account = new Account(1L, "jon doe", "M", new Date());
        when(accountService.save(account)).thenReturn(account);
        assertNotNull(account);
    }

    @Test
    public void getList() {
        Account account1 = new Account(1L, "james doe", "M", new Date());
        Account account2 = new Account(2L, "james doe", "M", new Date());
        Account account3 = new Account(3L, "james doe", "M", new Date());
        List<Account> list = new ArrayList<>();
        list.add(account1);
        list.add(account2);
        list.add(account3);
        when(accountService.getList()).thenReturn(list);
        assertEquals(list.size(),3);
    }
}