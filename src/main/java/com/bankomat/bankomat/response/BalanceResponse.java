package com.bankomat.bankomat.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceResponse {

    private String message;
    private BigDecimal balanceByn;
    private BigDecimal balanceUsd;
    private BigDecimal balanceEur;


}
