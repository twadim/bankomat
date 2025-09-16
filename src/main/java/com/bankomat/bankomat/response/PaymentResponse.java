package com.bankomat.bankomat.response;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class PaymentResponse {

    public PaymentResponse(Boolean success, String message, String serviceName, BigDecimal amountPaid,  BigDecimal remainingBalanceByn,  BigDecimal remainingBalanceUsd,  BigDecimal remainingBalanceEur){
        this.success = success;
        this.message = message;
        this.serviceName = serviceName;
        this.amountPaid = amountPaid;
        this.remainingBalanceByn = remainingBalanceByn;
        this.remainingBalanceUsd = remainingBalanceUsd;
        this.remainingBalanceEur = remainingBalanceEur;
    }

    public PaymentResponse(Boolean success, String message){
        this.success = success;
        this.message = message;
        this.serviceName = null;
        this.amountPaid = BigDecimal.ZERO;
        this.remainingBalanceByn = BigDecimal.ZERO;
        this.remainingBalanceUsd = BigDecimal.ZERO;
        this.remainingBalanceEur = BigDecimal.ZERO;
    }


    private Boolean success;
    private String message;
    private String serviceName;
    private BigDecimal amountPaid;
    private BigDecimal remainingBalanceByn;
    private BigDecimal remainingBalanceUsd;
    private BigDecimal remainingBalanceEur;


}
