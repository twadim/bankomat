package com.bankomat.bankomat.response;

import java.math.BigDecimal;

public class BalanceResponse {

    private BigDecimal balanceByn;
    private BigDecimal balanceUsd;
    private BigDecimal balanceEur;

    private String message;

    public BalanceResponse() {
    }

    public BalanceResponse(String message, BigDecimal balanceByn, BigDecimal balanceUsd, BigDecimal balanceEur) {
        this.message = message;
        this.balanceByn = balanceByn;
        this.balanceUsd = balanceUsd;
        this.balanceEur = balanceEur;
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

    @Override
    public String toString() {
        return "BalanceResponse{" +
                "balanceByn=" + balanceByn +
                ", balanceUsd=" + balanceUsd +
                ", balanceEur=" + balanceEur +
                '}';
    }
}
