package com.bankomat.bankomat.request;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class DepositRequest {

private String cardNumber;
private BigDecimal amount;
private String currency;    

} 
