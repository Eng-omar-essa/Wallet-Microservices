package io.gears.leo.service;

import io.gears.leo.exception.WalletException;

import java.util.List;

/**
 *
 * @author Omar Essa
 */

public interface PlayerService<T> {

    T save(T t) throws WalletException;
    T update(T t,Long id) throws WalletException ;
    List<T> getList();
}
