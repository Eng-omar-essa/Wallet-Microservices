package io.gears.leo.dao;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Omar Essa
 */
@Entity
public class Account extends BaseEntity {


    @NotNull
    @Size(min =5,message = "Player should have at least 2 character")
    private String playerName;
    private String gender;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreated;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Set<Wallet> transactions = new HashSet<>();

    public Account() {
    }

    public Account(Long Id) {
        this.id = Id;
    }



    public Account(AccountBuilder builder) {
        id = builder.id;
        playerName = builder.playerName;
        gender = builder.gender;
        dateCreated = builder.dateCreated;
    }

    public Account(Long id,String playerName, String gender, Date dateCreated) {
        this.id = id;
        this.playerName = playerName;
        this.gender = gender;
        this.dateCreated = dateCreated;
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

    public Set<Wallet> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Wallet> transactions) {
        this.transactions = transactions;
    }


    public static class AccountBuilder {

        private Long id;
        private String playerName;
        private String gender;
        private Date dateCreated;

        public Long getId() {
            return id;
        }

        public AccountBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public AccountBuilder setPlayerName(String playerName) {
            this.playerName = playerName;
            return this;
        }

        public AccountBuilder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public AccountBuilder setDateCreated(Date dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public Account build() {
            return new Account(this);
        }

    }
    @Override
    public String toString() {
        return "Account{" +
                "playerName='" + playerName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateCreated=" + dateCreated +
                ", transactions=" + transactions +
                ", id=" + id +
                '}';
    }
}
