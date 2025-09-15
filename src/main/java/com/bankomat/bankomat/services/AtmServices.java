package com.bankomat.bankomat.services;

import com.bankomat.bankomat.response.BalanceResponse;
import com.bankomat.bankomat.response.DepositResponse;
import com.bankomat.bankomat.response.UserResponse;
import com.bankomat.bankomat.response.WithdrawResponse;
import com.bankomat.bankomat.response.TransferResponse;
import com.bankomat.bankomat.response.PaymentResponse;
import java.math.BigDecimal;

public interface AtmServices {

    UserResponse userAuth(String cardNumber, String password);
    
    BalanceResponse getBalance(String cardNumber);

    WithdrawResponse withdrawMoney(String cardNumber, BigDecimal amount, String currency);

    DepositResponse depositMoney(String cardNumber, BigDecimal amount, String currency);

    TransferResponse transferMoney(String fromCardNumber, String toCardNumber, BigDecimal amount, String currency);

    PaymentResponse payForService(String cardNumber, String serviceCode, String currency);
}
