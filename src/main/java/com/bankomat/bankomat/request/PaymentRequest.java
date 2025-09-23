package com.bankomat.bankomat.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Запрос оплаты услуг")
public class PaymentRequest {

    @Schema(description = "Номер карты", example = "1111111111111111", required = true)
    @NotBlank(message = "Номер карты обязателен")
    @Pattern(regexp = "^\\d{16}$", message = "Номер карты должен содержать 16 цифр")
    private String cardNumber;

    @Schema(description = "Код услуги", example = "ELEC001", required = true)
    @NotBlank(message = "Код услуги обязателен")
    private String serviceCode;

    @Schema(description = "Валюта", example = "byn", required = true)
    @NotBlank(message = "Валюта обязательна")
    @Pattern(regexp = "(?i)(byn|usd|eur)", message = "Валюта должна быть BYN, USD или EUR")
    private String currency;


}
