package com.bankomat.bankomat.service;

import com.bankomat.bankomat.request.BalanceRequest;
import com.bankomat.bankomat.request.DepositRequest;
import com.bankomat.bankomat.request.PaymentRequest;
import com.bankomat.bankomat.request.TransferRequest;
import com.bankomat.bankomat.request.UserAuthRequest;
import com.bankomat.bankomat.request.WithdrawRequest;
import com.bankomat.bankomat.response.BalanceResponse;
import com.bankomat.bankomat.response.DepositResponse;
import com.bankomat.bankomat.response.UserResponse;
import com.bankomat.bankomat.response.WithdrawResponse;
import com.bankomat.bankomat.response.TransferResponse;
import com.bankomat.bankomat.response.PaymentResponse;


public interface AtmServices {

    UserResponse userAuth(UserAuthRequest userAuthRequest);
    
    BalanceResponse getBalance(BalanceRequest balanceRequest);

    WithdrawResponse withdrawMoney(WithdrawRequest withdrawRequest);

    DepositResponse depositMoney(DepositRequest depositRequest);

    TransferResponse transferMoney(TransferRequest transferRequest);

    PaymentResponse payForService(PaymentRequest paymentRequest);
}
