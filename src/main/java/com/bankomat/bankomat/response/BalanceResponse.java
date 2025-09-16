package com.bankomat.bankomat.response;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class BalanceResponse {

    private BigDecimal balanceByn;
    private BigDecimal balanceUsd;
    private BigDecimal balanceEur;
    private String message;

    public BalanceResponse(String message, BigDecimal balanceByn, BigDecimal balanceUsd, BigDecimal balanceEur) {
        this.message = message;
        this.balanceByn = balanceByn;
        this.balanceUsd = balanceUsd;
        this.balanceEur = balanceEur;
    }


}
