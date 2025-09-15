package com.bankomat.bankomat.response;

import java.math.BigDecimal;

public class TransferResponse {

    private Boolean success;
    private String message;
    private BigDecimal senderBalanceByn;
    private BigDecimal senderBalanceUsd;
    private BigDecimal senderBalanceEur;
    private BigDecimal receiverBalanceByn;
    private BigDecimal receiverBalanceUsd;
    private BigDecimal receiverBalanceEur;

    public TransferResponse(){
    }

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

    // геттеры и сеттеры
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

    public BigDecimal getSenderBalanceByn() {
        return senderBalanceByn;
    }

    public void setSenderBalanceByn(BigDecimal senderBalanceByn) {
        this.senderBalanceByn = senderBalanceByn;
    }

    public BigDecimal getSenderBalanceUsd() {
        return senderBalanceUsd;
    }

    public void setSenderBalanceUsd(BigDecimal senderBalanceUsd) {
        this.senderBalanceUsd = senderBalanceUsd;
    }

    public BigDecimal getSenderBalanceEur() {
        return senderBalanceEur;
    }

    public void setSenderBalanceEur(BigDecimal senderBalanceEur) {
        this.senderBalanceEur = senderBalanceEur;
    }

    public BigDecimal getReceiverBalanceByn() {
        return receiverBalanceByn;
    }

    public void setReceiverBalanceByn(BigDecimal receiverBalanceByn) {
        this.receiverBalanceByn = receiverBalanceByn;
    }

    public BigDecimal getReceiverBalanceUsd() {
        return receiverBalanceUsd;
    }

    public void setReceiverBalanceUsd(BigDecimal receiverBalanceUsd) {
        this.receiverBalanceUsd = receiverBalanceUsd;
    }

    public BigDecimal getReceiverBalanceEur() {
        return receiverBalanceEur;
    }

    public void setReceiverBalanceEur(BigDecimal receiverBalanceEur) {
        this.receiverBalanceEur = receiverBalanceEur;
    }

    @Override
    public String toString() {
        return "TransferResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", senderBalanceByn=" + senderBalanceByn +
                ", senderBalanceUsd=" + senderBalanceUsd +
                ", senderBalanceEur=" + senderBalanceEur +
                ", receiverBalanceByn=" + receiverBalanceByn +
                ", receiverBalanceUsd=" + receiverBalanceUsd +
                ", receiverBalanceEur=" + receiverBalanceEur +
                '}';
    }
}