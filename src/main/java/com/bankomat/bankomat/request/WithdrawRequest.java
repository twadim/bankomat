package com.bankomat.bankomat.request;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class WithdrawRequest {

    private String cardNumber;
    private BigDecimal amount;
    private String currency;

}