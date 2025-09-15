package com.bankomat.bankomat.response;

import java.math.BigDecimal;

public class DepositResponse {

    private Boolean success;
    private String message;
    private BigDecimal remainingBalanceByn;
    private BigDecimal remainingBalanceUsd;
    private BigDecimal remainingBalanceEur;

    public DepositResponse(){   
    }

    public DepositResponse(Boolean success, String message, BigDecimal remainingBalanceByn, BigDecimal remainingBalanceUsd, BigDecimal remainingBalanceEur){
        this.success = success;
        this.message = message;
        this.remainingBalanceByn = remainingBalanceByn;
        this.remainingBalanceUsd = remainingBalanceUsd;
        this.remainingBalanceEur = remainingBalanceEur;
    }

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
        return "DepositResponse{" +
                "success=" + success +
                ", message=" + message +
                ", remainingBalanceByn=" + remainingBalanceByn +
                ", remainingBalanceUsd=" + remainingBalanceUsd +
                ", remainingBalanceEur=" + remainingBalanceEur +
                '}';
    }

}
