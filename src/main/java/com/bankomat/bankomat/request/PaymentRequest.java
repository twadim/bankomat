package com.bankomat.bankomat.request;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class PaymentRequest {


    private String cardNumber;
    private String serviceCode;
    private String currency;


}
