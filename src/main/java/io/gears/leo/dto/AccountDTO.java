package io.gears.leo.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Omar Essa
 */

public class AccountDTO {

    private Long id;
    private String playerName;
    private String gender;
    private Date dateCreated;
    private BigDecimal balance;

    public AccountDTO() {
    }

    public AccountDTO(AccountDTOBuilder builder) {
        id = builder.id;
        playerName = builder.playerName;
        gender = builder.sex;
        dateCreated = builder.dateCreated;
        balance = builder.balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public static class AccountDTOBuilder {

        private Long id;
        private String playerName;
        private String sex;
        private Date dateCreated;
        private BigDecimal balance;

        public AccountDTOBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public AccountDTOBuilder setPlayerName(String playerName) {
            this.playerName = playerName;
            return this;
        }

        public AccountDTOBuilder setSex(String sex) {
            this.sex = sex;
            return this;
        }

        public AccountDTOBuilder setDateCreated(Date dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public AccountDTOBuilder setBalance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public AccountDTO build() {
            return new AccountDTO(this);
        }

    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "id=" + id +
                ", playerName='" + playerName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateCreated=" + dateCreated +
                ", balance=" + balance +
                '}';
    }
}
