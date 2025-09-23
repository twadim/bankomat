package com.bankomat.bankomat.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Запрос на пополнение счета")
public class DepositRequest {

    @Schema(description = "Номер карты", example = "1111111111111111", required = true)
    @NotBlank(message = "Номер карты обязателен")
    @Pattern(regexp = "^\\d{16}$", message = "Номер карты должен содержать 16 цифр")
    private String cardNumber;

    @Schema(description = "Сумма", example = "200", required = true)
    @Positive(message = "Сумма должна быть положительной")
    private BigDecimal amount;

    @Schema(description = "Валюта", example = "byn", required = true)
    @NotBlank(message = "Валюта обязательна")
    @Pattern(regexp = "(?i)(byn|usd|eur)", message = "Валюта должна быть BYN, USD или EUR")
    private String currency;

} 
