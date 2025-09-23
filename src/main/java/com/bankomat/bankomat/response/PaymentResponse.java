package com.bankomat.bankomat.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

    private Boolean success;
    private String message;
    private String serviceName;
    private BigDecimal amountPaid;
    private BigDecimal remainingBalanceByn;
    private BigDecimal remainingBalanceUsd;
    private BigDecimal remainingBalanceEur;

    public PaymentResponse(Boolean success, String message){
        this.success = success;
        this.message = message;
        this.serviceName = null;
        this.amountPaid = BigDecimal.ZERO;
        this.remainingBalanceByn = BigDecimal.ZERO;
        this.remainingBalanceUsd = BigDecimal.ZERO;
        this.remainingBalanceEur = BigDecimal.ZERO;
    }


}
