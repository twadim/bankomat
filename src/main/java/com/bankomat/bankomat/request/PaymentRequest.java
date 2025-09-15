package com.bankomat.bankomat.request;

import java.math.BigDecimal;

public class PaymentRequest {

    public PaymentRequest(){
    }

    public PaymentRequest(String cardNumber, String serviceCode, String currency){
        this.cardNumber = cardNumber;
        this.serviceCode = serviceCode;
        this.currency = currency;
    }       
    
    private String cardNumber;
    private String serviceCode;
    private String currency;

    public String getCardNumber() {
        return cardNumber;
    }

    public String getServiceCode() {
        return serviceCode;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    

}
