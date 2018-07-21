package io.gears.leo.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Omar Essa
 */

@Entity
public class Wallet extends BaseEntity {


    @ManyToOne
    private Account account;

    @NotNull
    private BigDecimal amount;
    private String purpose;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date transactionDate;
    @NotNull
    @Column(unique = true)
    private Long transactionReference;


    public Wallet() {
    }

    public Wallet(Account account, BigDecimal amount, String purpose, Date transactionDate, Long transactionReference) {
        this.account = account;
        this.amount = amount;
        this.purpose = purpose;
        this.transactionDate = transactionDate;
        this.transactionReference = transactionReference;
    }



    public Wallet(WalletBuilder builder) {
        id = builder.id;
        account = new Account(builder.accountId);
        amount = builder.amount;
        purpose = builder.purpose;
        transactionDate = builder.transactionDate;
        transactionReference = builder.transactionReference;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(Long transactionReference) {
        this.transactionReference = transactionReference;
    }



    public static class WalletBuilder {

        private Long id;
        private Long accountId;
        private BigDecimal amount;
        private String purpose;
        private Date transactionDate;
        private Long transactionReference;

        public WalletBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public WalletBuilder setAccount(Long accountId) {
            this.accountId = accountId;
            return this;
        }

        public WalletBuilder setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public WalletBuilder setPurpose(String purpose) {
            this.purpose = purpose;
            return this;
        }

        public WalletBuilder setTransactionDate(Date transactionDate) {
            this.transactionDate = transactionDate;
            return this;
        }

        public WalletBuilder setTransactionReference(Long transactionReference) {
            this.transactionReference = transactionReference;
            return this;
        }

        public Wallet build() {
            return new Wallet(this);
        }

    }


    @Override
    public String toString() {
        return "Wallet{" +
                "account=" + account +
                ", amount=" + amount +
                ", purpose='" + purpose + '\'' +
                ", transactionDate=" + transactionDate +
                ", transactionReference=" + transactionReference +
                ", id=" + id +
                '}';
    }
}
