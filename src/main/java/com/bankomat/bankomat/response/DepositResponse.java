package com.bankomat.bankomat.response;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class DepositResponse {

    private Boolean success;
    private String message;
    private BigDecimal remainingBalanceByn;
    private BigDecimal remainingBalanceUsd;
    private BigDecimal remainingBalanceEur;



    public DepositResponse(Boolean success, String message, BigDecimal remainingBalanceByn, BigDecimal remainingBalanceUsd, BigDecimal remainingBalanceEur){
        this.success = success;
        this.message = message;
        this.remainingBalanceByn = remainingBalanceByn;
        this.remainingBalanceUsd = remainingBalanceUsd;
        this.remainingBalanceEur = remainingBalanceEur;
    }



}
