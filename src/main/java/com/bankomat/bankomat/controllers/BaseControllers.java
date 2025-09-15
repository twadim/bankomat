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
import com.bankomat.bankomat.services.impl.AtmServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BaseControllers {

    private final AtmServiceImpl atmServiceImpl;

    public BaseControllers(AtmServiceImpl atmServiceImpl) {
        this.atmServiceImpl = atmServiceImpl;
    }



    @PostMapping(value = "/auth")
    public ResponseEntity<UserResponse> AuthUser(@RequestBody UserAuthRequest userAuthRequest) {


        UserResponse userResponse = atmServiceImpl.userAuth(userAuthRequest.getCardNumber(),userAuthRequest.getPassword());

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }


    @GetMapping(value = "/balance/{cardNumber}")
    public ResponseEntity<BalanceResponse> getBalance(@PathVariable String cardNumber) {

        BalanceResponse balance = atmServiceImpl.getBalance(cardNumber);
        
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    

    @PostMapping(value = "/withdraw")
    public ResponseEntity<WithdrawResponse> withdrawMoney(@RequestBody WithdrawRequest withdrawRequest) {

        WithdrawResponse withdrawResponse = atmServiceImpl.withdrawMoney(withdrawRequest.getCardNumber(), withdrawRequest.getAmount(), withdrawRequest.getCurrency());
        
        return new ResponseEntity<>(withdrawResponse, HttpStatus.OK);

    }

    @PostMapping(value = "/deposit")
    public ResponseEntity<DepositResponse> depositMoney(@RequestBody DepositRequest depositRequest) {

        DepositResponse depositResponse = atmServiceImpl.depositMoney(depositRequest.getCardNumber(), depositRequest.getAmount(), depositRequest.getCurrency());
        
        return new ResponseEntity<>(depositResponse, HttpStatus.OK);
    }


    @PostMapping(value = "/transfer")
public ResponseEntity<TransferResponse> transferMoney(@RequestBody TransferRequest transferRequest) {
    TransferResponse transferResponse = atmServiceImpl.transferMoney(
        transferRequest.getFromCardNumber(), 
        transferRequest.getToCardNumber(), 
        transferRequest.getAmount(), 
        transferRequest.getCurrency()
    );
    return new ResponseEntity<>(transferResponse, HttpStatus.OK);
}

@PostMapping(value = "/payment")
public ResponseEntity<PaymentResponse> payForService(@RequestBody PaymentRequest paymentRequest) {
    PaymentResponse paymentResponse = atmServiceImpl.payForService(paymentRequest.getCardNumber(), paymentRequest.getServiceCode(), paymentRequest.getCurrency());
    return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
}

}
