package com.bankomat.bankomat.controllers;

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
import com.bankomat.bankomat.services.AtmServices;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Bankomat API", description = "API для операций банкомата")
public class BaseControllers {

    private final AtmServices atmServices;

    public BaseControllers(AtmServices atmServices) {
        this.atmServices = atmServices;
    }



    @PostMapping(value = "/auth")
    @Operation(
        summary = "Аутентификация пользователя",
        description = "Проверяет номер карты и PIN-код пользователя"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешная аутентификация"),
        @ApiResponse(responseCode = "400", description = "Неверные данные"),
        @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    public ResponseEntity<UserResponse> AuthUser(
        @Parameter(description = "Данные для аутентификации", required = true)
        @Valid @RequestBody UserAuthRequest userAuthRequest) {


        UserResponse userResponse = atmServices.userAuth(userAuthRequest.getCardNumber(),userAuthRequest.getPassword());

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/balance/{cardNumber}")
    @Operation(
        summary = "Получение баланса",
        description = "Возвращает баланс пользователя в трех валютах (BYN, USD, EUR)"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Баланс получен успешно"),
        @ApiResponse(responseCode = "400", description = "Неверные данные"),
        @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    public ResponseEntity<BalanceResponse> getBalance(
        @Parameter(description = "Номер карты", required = true)
        @PathVariable String cardNumber) {

        BalanceResponse balance = atmServices.getBalance(cardNumber);
        
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    

    @PostMapping(value = "/withdraw")
    @Operation(
        summary = "Снятие денег",
        description = "Снимает указанную сумму с баланса пользователя"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Деньги сняты успешно"),
        @ApiResponse(responseCode = "400", description = "Неверные данные или недостаточно средств"),
        @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    public ResponseEntity<WithdrawResponse> withdrawMoney(
        @Parameter(description = "Данные для снятия денег", required = true)
        @Valid @RequestBody WithdrawRequest withdrawRequest) {

        WithdrawResponse withdrawResponse = atmServices.withdrawMoney(withdrawRequest.getCardNumber(), withdrawRequest.getAmount(), withdrawRequest.getCurrency());
        
        return new ResponseEntity<>(withdrawResponse, HttpStatus.OK);

    }

    @PostMapping(value = "/deposit")
    @Operation(
        summary = "Пополнение счета",
        description = "Пополняет баланс пользователя на указанную сумму"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Счет пополнен успешно"),
        @ApiResponse(responseCode = "400", description = "Неверные данные"),
        @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    public ResponseEntity<DepositResponse> depositMoney(
        @Parameter(description = "Данные для пополнения счета", required = true)
        @Valid @RequestBody DepositRequest depositRequest) {

        DepositResponse depositResponse = atmServices.depositMoney(depositRequest.getCardNumber(), depositRequest.getAmount(), depositRequest.getCurrency());
        
        return new ResponseEntity<>(depositResponse, HttpStatus.OK);
    }


    @PostMapping(value = "/transfer")
    @Operation(
        summary = "Перевод денег",
        description = "Переводит деньги с одного счета на другой"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Перевод выполнен успешно"),
        @ApiResponse(responseCode = "400", description = "Неверные данные или недостаточно средств"),
        @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    public ResponseEntity<TransferResponse> transferMoney(
        @Parameter(description = "Данные для перевода", required = true)
        @Valid @RequestBody TransferRequest transferRequest) {
    TransferResponse transferResponse = atmServices.transferMoney(
        transferRequest.getFromCardNumber(), 
        transferRequest.getToCardNumber(), 
        transferRequest.getAmount(), 
        transferRequest.getCurrency()
    );
    return new ResponseEntity<>(transferResponse, HttpStatus.OK);
}

    @PostMapping(value = "/payment")
    @Operation(
        summary = "Оплата услуг",
        description = "Оплачивает услуги по коду услуги"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Оплата выполнена успешно"),
        @ApiResponse(responseCode = "400", description = "Неверные данные или недостаточно средств"),
        @ApiResponse(responseCode = "404", description = "Пользователь или услуга не найдены")
    })
    public ResponseEntity<PaymentResponse> payForService(
        @Parameter(description = "Данные для оплаты", required = true)
        @Valid @RequestBody PaymentRequest paymentRequest) {
    PaymentResponse paymentResponse = atmServices.payForService(paymentRequest.getCardNumber(), paymentRequest.getServiceCode(), paymentRequest.getCurrency());
    return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
}

}
