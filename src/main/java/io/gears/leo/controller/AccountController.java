package io.gears.leo.controller;


import io.gears.leo.dao.Account;
import io.gears.leo.dao.Wallet;
import io.gears.leo.dto.AccountDTO;
import io.gears.leo.dto.WalletDTO;
import io.gears.leo.exception.InsufficientBalanceException;
import io.gears.leo.exception.WalletException;
import io.gears.leo.mapper.AccountMapper;
import io.gears.leo.mapper.WalletMapper;
import io.gears.leo.service.AccountService;
import io.gears.leo.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Omar Essa
 */

@RestController
@RequestMapping("/api/v1/players")
public class AccountController {

    private static final Logger logger = Logger.getLogger(AccountController.class.getName());

    @Autowired
    private AccountService accountService;

    @Autowired
    private WalletService walletService;

    @GetMapping
    public ResponseEntity getPlayers() {
        List<Account> accounts = accountService.getList();
        return new ResponseEntity<List<AccountDTO>>(AccountMapper.doToDTOList(accounts), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPlayer(@Valid @PathVariable("id") Long id) {
       Optional<Account> account;
        try {
            account = accountService.accountByPK(id);
        } catch (WalletException ex) {
            logger.log(Level.SEVERE, null, ex);
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AccountDTO>(AccountMapper.convertToDto(account), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity createPlayer(@Valid @RequestBody AccountDTO accountDTO) {
        Account saved;
        try {
            saved = accountService.save(AccountMapper.convertToEnitiy(accountDTO));
        } catch (WalletException ex) {
            logger.log(Level.SEVERE, null, ex);
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<AccountDTO>(AccountMapper.convertToDto(Optional.ofNullable(saved)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePlayer( @Valid @PathVariable("id") Long accountId, @RequestBody AccountDTO accountDTO) {
        Account saved;
        try {
            saved = accountService.update(AccountMapper.convertToEnitiy(accountDTO), accountId);
        } catch (WalletException ex) {
            logger.log(Level.SEVERE, null, ex);
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<AccountDTO>(AccountMapper.convertToDto(Optional.ofNullable(saved)), HttpStatus.OK);
    }

    @PostMapping("/{id}/transactions")
    public ResponseEntity createTransaction(@PathVariable("id")Long accountId,@RequestBody WalletDTO walletDTO) {
        Wallet saved;
        try {
            walletDTO.setAccountId(accountId);
            saved = walletService.createTransaction(WalletMapper.convertToEntity(walletDTO));
        } catch (WalletException | InsufficientBalanceException ex) {
            logger.log(Level.SEVERE, null, ex);
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<WalletDTO>(WalletMapper.convertToDTO(saved), HttpStatus.CREATED);
    }


}
