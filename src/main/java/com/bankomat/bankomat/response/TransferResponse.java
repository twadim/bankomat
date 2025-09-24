package com.bankomat.bankomat.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ответ на запрос перевода денег")
public class TransferResponse {

    @Schema(description = "Успешность операции", example = "true")
    private boolean success;

    @Schema(description = "Сообщение о результате", example = "Перевод выполнен успешно")
    private String message;

    @Schema(description = "Баланс в BYN", example = "50000.00")
    private BigDecimal senderBalanceByn;

    @Schema(description = "Баланс в USD", example = "50000.00")
    private BigDecimal senderBalanceUsd;

    @Schema(description = "Баланс в EUR", example = "50000.00")
    private BigDecimal senderBalanceEur;

    @Schema(description = "Баланс в BYN", example = "50000.00")
    private BigDecimal receiverBalanceByn;

    @Schema(description = "Баланс в USD", example = "50000.00")
    private BigDecimal receiverBalanceUsd;

    @Schema(description = "Баланс в EUR", example = "50000.00")
    private BigDecimal receiverBalanceEur;



    public TransferResponse(boolean success, String message){
        this.success = success;
    this.message = message;
    this.senderBalanceByn = BigDecimal.ZERO;
    this.senderBalanceUsd = BigDecimal.ZERO;
    this.senderBalanceEur = BigDecimal.ZERO;
    this.receiverBalanceByn = BigDecimal.ZERO;
    this.receiverBalanceUsd = BigDecimal.ZERO;
    this.receiverBalanceEur = BigDecimal.ZERO;
    }


}