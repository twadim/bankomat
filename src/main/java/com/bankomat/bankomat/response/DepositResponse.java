package com.bankomat.bankomat.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositResponse {

    private Boolean success;
    private String message;
    private BigDecimal remainingBalanceByn;
    private BigDecimal remainingBalanceUsd;
    private BigDecimal remainingBalanceEur;

}
