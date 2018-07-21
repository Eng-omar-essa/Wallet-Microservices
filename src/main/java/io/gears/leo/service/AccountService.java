package io.gears.leo.service;

import io.gears.leo.dao.Account;

import io.gears.leo.exception.WalletException;

import java.util.Optional;

/**
 *
 * @author Omar Essa
 * This impelemntation of Account Service interface
 *
 */

public interface AccountService extends  PlayerService<Account>{

    Optional<Account> accountByPK(Long accountId) throws WalletException;

}
