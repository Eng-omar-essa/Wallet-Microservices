package io.gears.leo.mapper;

import io.gears.leo.dao.Account;
import io.gears.leo.dto.AccountDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author Omar Essa
 * This is an AccountMapper , it is used to convert form Entity to DTO and vice versa.
 */

@Component
public class AccountMapper {

    public static Account convertToEnitiy(AccountDTO accountDTO) {
        Account account = new Account.AccountBuilder()
                .setDateCreated(new Date())
                .setId(accountDTO.getId())
                .setPlayerName(accountDTO.getPlayerName())
                .setGender(accountDTO.getGender())
                .build();
        return account;
    }

    public static AccountDTO convertToDto(Optional<Account> account_) {
        AccountDTO dto = null;
      if(account_.isPresent()){
        Account account = account_.get();
          double balance = account.getTransactions().stream().mapToDouble(o -> o.getAmount().doubleValue()).sum();
          dto = new AccountDTO.AccountDTOBuilder().setId(account.getId())
                  .setDateCreated(account.getDateCreated())
                  .setPlayerName(account.getPlayerName())
                  .setId(account.getId())
                  .setSex(account.getGender())
                  .setBalance(new BigDecimal(balance))
                  .build();
      }


        return dto;
    }

    public static List<AccountDTO> doToDTOList(List<Account> account) {
              return account.stream()
                .map((Account account_) -> convertToDto(Optional.ofNullable(account_)))
                .collect(Collectors.toList());
    }

}
