package com.bankomat.bankomat.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="accounts")
public class AccountEntity {

    public AccountEntity(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long accountId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="balance_byn")
    private BigDecimal balanceByn;

    @Column(name="balance_usd")
    private BigDecimal balanceUsd;

    @Column(name="balance_eur")
    private BigDecimal balanceEur;

    @Override
    public String toString() {
        return "AccountEntity{" +
                "accountId=" + accountId +
                ", userId=" + userId +
                ", balanceByn=" + balanceByn +
                ", balanceUsd=" + balanceUsd +
                ", balanceEur=" + balanceEur +
                '}';
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getBalanceByn() {
        return balanceByn;
    }

    public void setBalanceByn(BigDecimal balanceByn) {
        this.balanceByn = balanceByn;
    }

    public BigDecimal getBalanceUsd() {
        return balanceUsd;
    }

    public void setBalanceUsd(BigDecimal balanceUsd) {
        this.balanceUsd = balanceUsd;
    }

    public BigDecimal getBalanceEur() {
        return balanceEur;
    }

    public void setBalanceEur(BigDecimal balanceEur) {
        this.balanceEur = balanceEur;
    }
}
