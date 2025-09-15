package com.bankomat.bankomat.request;

import java.math.BigDecimal;

public class TransferRequest {

    public TransferRequest(){
    }

    
    public TransferRequest(String fromCardNumber, String toCardNumber, BigDecimal amount, String currency){
        this.fromCardNumber = fromCardNumber;
        this.toCardNumber = toCardNumber;
        this.amount = amount;
        this.currency = currency;
    }

    private String fromCardNumber;
    private String toCardNumber;
    private BigDecimal amount;
    private String currency;

    public String getFromCardNumber() {
        return fromCardNumber;
    }
    
    public String getToCardNumber() {
        return toCardNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setFromCardNumber(String fromCardNumber) {
        this.fromCardNumber = fromCardNumber;
    }

    public void setToCardNumber(String toCardNumber) {
        this.toCardNumber = toCardNumber;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    @Override
    public String toString() {
        return "TransferRequest{" +
                "fromCardNumber=" + fromCardNumber +
                ", toCardNumber=" + toCardNumber +
                ", amount=" + amount +
                ", currency=" + currency +
                '}';
    }
}


