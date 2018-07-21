package io.gears.leo.repos;

import io.gears.leo.dao.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepositry extends CrudRepository<Account, Long> {

    Optional<Account> getByPlayerName(String name);
}
