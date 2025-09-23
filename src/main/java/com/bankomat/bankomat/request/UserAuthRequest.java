package com.bankomat.bankomat.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Запрос на авторизацию")
public class UserAuthRequest
{
    @Schema(description = "Номер карты", example = "1111111111111111", required = true)
    @NotBlank(message = "Номер карты обязателен")
    @Pattern(regexp = "^\\d{16}$", message = "Номер карты должен содержать 16 цифр")
    private String cardNumber;

    @Schema(description = "Пароль", example = "1234", required = true)
    @NotBlank(message = "Пароль обязателен")
    @Pattern(regexp = "^\\d{4}$", message = "Пароль должен содержать 4 цифры")
    private String password;


}
