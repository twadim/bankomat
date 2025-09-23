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
@Schema(description = "Запрос на перевод денег")
public class TransferRequest {

    @Schema(description = "Номер карты отправителя", example = "1111111111111111", required = true)
    @NotBlank(message = "Номер карты отправителя обязателен")
    @Pattern(regexp = "^\\d{16}$", message = "Номер карты отправителя должен содержать 16 цифр")
    private String fromCardNumber;

    @Schema(description = "Номер карты получателя", example = "2222222222222222", required = true)
    @NotBlank(message = "Номер карты получателя обязателен")
    @Pattern(regexp = "^\\d{16}$", message = "Номер карты получателя должен содержать 16 цифр")
    private String toCardNumber;

    @Schema(description = "Сумма", example = "500", required = true)
    @Positive(message = "Сумма должна быть положительной")
    private BigDecimal amount;

    @Schema(description = "Валюта", example = "byn", required = true)
    @NotBlank(message = "Валюта обязательна")
    @Pattern(regexp = "(?i)(byn|usd|eur)", message = "Валюта должна быть BYN, USD или EUR")
    private String currency;

}


