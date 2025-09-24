package com.bankomat.bankomat.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ответ на запрос оплаты услуг")
public class PaymentResponse {

    @Schema(description = "Успешность операции", example = "true")
    private boolean success;

    @Schema(description = "Сообщение о результате", example = "Оплата выполнена успешно")
    private String message;

    @Schema(description = "Название услуги", example = "Электроэнергия")
    private String serviceName;

    @Schema(description = "Сумма оплаты", example = "100.00")
    private BigDecimal amountPaid;

    @Schema(description = "Баланс в BYN", example = "50000.00")
    private BigDecimal remainingBalanceByn;

    @Schema(description = "Баланс в USD", example = "50000.00")
    private BigDecimal remainingBalanceUsd;

    @Schema(description = "Баланс в EUR", example = "50000.00")
    private BigDecimal remainingBalanceEur;

    public PaymentResponse(boolean success, String message){
        this.success = success;
        this.message = message;
        this.serviceName = null;
        this.amountPaid = BigDecimal.ZERO;
        this.remainingBalanceByn = BigDecimal.ZERO;
        this.remainingBalanceUsd = BigDecimal.ZERO;
        this.remainingBalanceEur = BigDecimal.ZERO;
    }


}
