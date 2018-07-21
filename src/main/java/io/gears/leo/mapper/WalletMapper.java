package io.gears.leo.mapper;

import io.gears.leo.dao.Wallet;
import io.gears.leo.dto.WalletDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Omar Essa
 * This is an WalletMapper , it is used to convert form Entity to DTO and vice versa.
 */

@Component
public class WalletMapper {

    public static Wallet convertToEntity(WalletDTO walletDTO) {
        Wallet wallet = new Wallet.WalletBuilder()
                .setAccount(walletDTO.getAccountId())
                .setAmount(walletDTO.getAmount())
                .setId(walletDTO.getId())
                .setPurpose(walletDTO.getPurpose())
                .setTransactionDate(walletDTO.getTransactionDate())
                .setTransactionReference(walletDTO.getTransactionReference()).build();
        return wallet;
    }

    public static WalletDTO convertToDTO(Wallet wallet) {
        WalletDTO walletDTO = new WalletDTO.WalletDTOBuilder()
                .setAccountId(wallet.getAccount().getId())
                .setAmount(wallet.getAmount())
                .setId(wallet.getId())
                .setPurpose(wallet.getPurpose())
                .setTransactionDate(wallet.getTransactionDate())
                .setTransactionReference(wallet.getTransactionReference()).build();
        return walletDTO;
    }

    public static List<WalletDTO> doToDTOList(List<Wallet> txns) {
        return txns.stream()
                .map(WalletMapper::convertToDTO)
                .collect(Collectors.toList());

    }
}
