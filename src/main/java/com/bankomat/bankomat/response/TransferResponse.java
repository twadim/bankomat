package com.bankomat.bankomat.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferResponse {

    private Boolean success;
    private String message;
    private BigDecimal senderBalanceByn;
    private BigDecimal senderBalanceUsd;
    private BigDecimal senderBalanceEur;
    private BigDecimal receiverBalanceByn;
    private BigDecimal receiverBalanceUsd;
    private BigDecimal receiverBalanceEur;



    public TransferResponse(Boolean success, String message){
        this.success = success;
    this.message = message;
    this.senderBalanceByn = BigDecimal.ZERO;
    this.senderBalanceUsd = BigDecimal.ZERO;
    this.senderBalanceEur = BigDecimal.ZERO;
    this.receiverBalanceByn = BigDecimal.ZERO;
    this.receiverBalanceUsd = BigDecimal.ZERO;
    this.receiverBalanceEur = BigDecimal.ZERO;
    }


}