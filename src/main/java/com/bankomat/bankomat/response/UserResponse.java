package com.bankomat.bankomat.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ответ на запрос пользователя")
public class UserResponse {

    @Schema(description = "Имя пользователя", example = "John Doe")
    private String userName;

    @Schema(description = "Баланс в BYN", example = "50000.00")
    private BigDecimal balanceByn;  

    @Schema(description = "Баланс в USD", example = "50000.00")
    private BigDecimal balanceUsd;

    @Schema(description = "Баланс в EUR", example = "50000.00")
    private BigDecimal balanceEur;


}
