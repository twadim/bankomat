package com.bankomat.bankomat.response;

import java.math.BigDecimal;

public class PaymentResponse {


    public PaymentResponse(){
    }

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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }
    
        public BigDecimal getRemainingBalanceByn() {
        return remainingBalanceByn;
    }

    public void setRemainingBalanceByn(BigDecimal remainingBalanceByn) {
        this.remainingBalanceByn = remainingBalanceByn;
    }
    
    public BigDecimal getRemainingBalanceUsd() {
        return remainingBalanceUsd;
    }

    public void setRemainingBalanceUsd(BigDecimal remainingBalanceUsd) {
        this.remainingBalanceUsd = remainingBalanceUsd;
    }
    
    public BigDecimal getRemainingBalanceEur() {
        return remainingBalanceEur;
    }

    public void setRemainingBalanceEur(BigDecimal remainingBalanceEur) {
        this.remainingBalanceEur = remainingBalanceEur;
    }   

    @Override
    public String toString() {
        return "PaymentResponse{" +
                "success=" + success +
                ", message=" + message +
                ", serviceName=" + serviceName +
                ", amountPaid=" + amountPaid +
                ", remainingBalanceByn=" + remainingBalanceByn +
                ", remainingBalanceUsd=" + remainingBalanceUsd +
                ", remainingBalanceEur=" + remainingBalanceEur +
                '}';
    }

}
