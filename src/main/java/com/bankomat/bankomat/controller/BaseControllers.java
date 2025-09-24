package com.bankomat.bankomat.controller;

import com.bankomat.bankomat.request.BalanceRequest;
import com.bankomat.bankomat.request.DepositRequest;
import com.bankomat.bankomat.request.PaymentRequest;
import com.bankomat.bankomat.request.TransferRequest;
import com.bankomat.bankomat.request.UserAuthRequest;
import com.bankomat.bankomat.request.WithdrawRequest;
import com.bankomat.bankomat.response.BalanceResponse;
import com.bankomat.bankomat.response.DepositResponse;
import com.bankomat.bankomat.response.PaymentResponse;
import com.bankomat.bankomat.response.TransferResponse;
import com.bankomat.bankomat.response.UserResponse;
import com.bankomat.bankomat.response.WithdrawResponse;
import com.bankomat.bankomat.service.AtmServices;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/atm")
@Tag(name = "Bankomat API", description = "API для операций банкомата")
public class BaseControllers {

    private final AtmServices atmServices;

    public BaseControllers(AtmServices atmServices) {
        this.atmServices = atmServices;
    }



    @PostMapping("/auth")
    @Operation(
        summary = "Аутентификация пользователя",
        description = "Проверяет номер карты и PIN-код пользователя"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешная аутентификация"),
        @ApiResponse(responseCode = "400", description = "Неверные данные"),
        @ApiResponse(responseCode = "401", description = "Пользователь не найден")
    })
    public ResponseEntity<UserResponse> authUser(
        @Parameter(description = "Данные для аутентификации", required = true)
        @Valid @RequestBody UserAuthRequest userAuthRequest) {

            return ResponseEntity.ok(atmServices.userAuth(userAuthRequest));

    }

    @PostMapping("/balance")
    @Operation(
        summary = "Получение баланса",
        description = "Возвращает баланс пользователя в трех валютах (BYN, USD, EUR)"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Баланс получен успешно"),
        @ApiResponse(responseCode = "400", description = "Неверные данные"),
        @ApiResponse(responseCode = "401", description = "Пользователь не найден")
    })
    public ResponseEntity<BalanceResponse> getBalance(
        @Parameter(description = "Номер карты", required = true)
        @RequestBody BalanceRequest balanceRequest) {

            return ResponseEntity.ok(atmServices.getBalance(balanceRequest));

       
    }

    

    @PostMapping("/withdraw")
    @Operation(
        summary = "Снятие денег",
        description = "Снимает указанную сумму с баланса пользователя"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Деньги сняты успешно"),
        @ApiResponse(responseCode = "400", description = "Неверные данные или недостаточно средств"),
        @ApiResponse(responseCode = "401", description = "Пользователь не найден")
    })
    public ResponseEntity<WithdrawResponse> withdrawMoney(
        @Parameter(description = "Данные для снятия денег", required = true)
        @Valid @RequestBody WithdrawRequest withdrawRequest) {

        return ResponseEntity.ok(atmServices.withdrawMoney(withdrawRequest));

    }

    @PostMapping("/deposit")
    @Operation(
        summary = "Пополнение счета",
        description = "Пополняет баланс пользователя на указанную сумму"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Счет пополнен успешно"),
        @ApiResponse(responseCode = "400", description = "Неверные данные"),
        @ApiResponse(responseCode = "401", description = "Пользователь не найден")
    })
    public ResponseEntity<DepositResponse> depositMoney(
        @Parameter(description = "Данные для пополнения счета", required = true)
        @Valid @RequestBody DepositRequest depositRequest) {

            return ResponseEntity.ok(atmServices.depositMoney(depositRequest));

    }


    @PostMapping("/transfer")
    @Operation(
        summary = "Перевод денег",
        description = "Переводит деньги с одного счета на другой"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Перевод выполнен успешно"),
        @ApiResponse(responseCode = "400", description = "Неверные данные или недостаточно средств"),
        @ApiResponse(responseCode = "401", description = "Пользователь не найден")
    })
    public ResponseEntity<TransferResponse> transferMoney(
        @Parameter(description = "Данные для перевода", required = true)
        @Valid @RequestBody TransferRequest transferRequest) {
    
    return ResponseEntity.ok(atmServices.transferMoney(transferRequest));
   
}

    @PostMapping("/payment")
    @Operation(
        summary = "Оплата услуг",
        description = "Оплачивает услуги по коду услуги"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Оплата выполнена успешно"),
        @ApiResponse(responseCode = "400", description = "Неверные данные или недостаточно средств"),
        @ApiResponse(responseCode = "401", description = "Пользователь или услуга не найдены")
    })
    public ResponseEntity<PaymentResponse> payForService(
        @Parameter(description = "Данные для оплаты", required = true)
        @Valid @RequestBody PaymentRequest paymentRequest) {

    return ResponseEntity.ok(atmServices.payForService(paymentRequest));

}

}
