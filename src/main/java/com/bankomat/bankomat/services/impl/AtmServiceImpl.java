package com.bankomat.bankomat.services.impl;

import com.bankomat.bankomat.entity.AccountEntity;
import com.bankomat.bankomat.entity.ServiceEntity;
import com.bankomat.bankomat.entity.UserEntity;
import com.bankomat.bankomat.repository.AccountRepository;
import com.bankomat.bankomat.repository.ServiceRepository;
import com.bankomat.bankomat.repository.UserRepository;
import com.bankomat.bankomat.response.BalanceResponse;
import com.bankomat.bankomat.response.DepositResponse;
import com.bankomat.bankomat.response.PaymentResponse;
import com.bankomat.bankomat.response.TransferResponse;
import com.bankomat.bankomat.response.UserResponse;
import com.bankomat.bankomat.response.WithdrawResponse;
import com.bankomat.bankomat.services.AtmServices;

import static com.bankomat.bankomat.services.CurrencyRates.*;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class AtmServiceImpl implements AtmServices {


    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    private final ServiceRepository serviceRepository;

    public AtmServiceImpl(UserRepository userRepository, AccountRepository accountRepository, ServiceRepository serviceRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public PaymentResponse payForService(String cardNumber, String serviceCode, String currency) {

        

        UserEntity user = userRepository.findUserEntityByCardNumber(cardNumber);

        if(user == null){
            return new PaymentResponse(false, "User not found");
        }

        AccountEntity account = accountRepository.findAccountEntityByUserId(user.getUserId());

        if(account == null){
            return new PaymentResponse(false, "Account not found");
        }

        ServiceEntity service = serviceRepository.findServiceEntityByServiceCodeAndIsActive(serviceCode, true);

        if(service == null){
            return new PaymentResponse(false, "Service not found or inactive");
        }

        BigDecimal currentBalance = account.getBalanceByn();
        BigDecimal servicePrice = service.getPriceByn();

        if(currentBalance.compareTo(servicePrice) < 0){

            return new PaymentResponse(false, "Insufficient funds");
        }
        else {

            account.setBalanceByn(currentBalance.subtract(servicePrice));
            accountRepository.save(account);
            return new PaymentResponse(true, "Payment completed", service.getServiceName(), servicePrice, account.getBalanceByn(), convertBynToUsd(account.getBalanceByn()), convertBynToEur(account.getBalanceByn()));
        }

    }

    @Override
    public UserResponse userAuth(String cardNumber, String password) {

        UserResponse userResponse = new UserResponse();

        UserEntity user = userRepository.findUserEntityByCardNumberAndPinCode(cardNumber, password);
        AccountEntity account = accountRepository.findAccountEntityByUserId(user.getUserId());

        userResponse.setUserName(user.getUserName());
        userResponse.setBalanceByn(account.getBalanceByn());
        userResponse.setBalanceUsd(convertBynToUsd(account.getBalanceByn()));
        userResponse.setBalanceEur(convertBynToEur(account.getBalanceByn()));

        return userResponse;

    }

    @Override
    public BalanceResponse getBalance(String cardNumber) {
        UserEntity user = userRepository.findUserEntityByCardNumber(cardNumber);

        if(user == null){

            return new BalanceResponse("User not found",BigDecimal.ZERO, BigDecimal.ZERO,BigDecimal.ZERO);
        }

        AccountEntity account = accountRepository.findAccountEntityByUserId(user.getUserId());
        return new BalanceResponse("Balance user "+user.getUserName(), account.getBalanceByn(), convertBynToUsd(account.getBalanceByn()), convertBynToEur(account.getBalanceByn()));
    }

    @Override
    public WithdrawResponse withdrawMoney(String cardNumber, BigDecimal amount, String currency){  
        
        // Проверяем пользователя
        UserEntity user = userRepository.findUserEntityByCardNumber(cardNumber);
        if(user == null){
            return new WithdrawResponse(false, "User not found", null, null, null);
        }         
        
        // Проверяем аккаунт
        AccountEntity account = accountRepository.findAccountEntityByUserId(user.getUserId());
        if(account == null){
            return new WithdrawResponse(false, "Account not found", null, null, null);
        }
        
        // Проверяем сумму
        if(amount.compareTo(BigDecimal.ZERO) <= 0){
            return new WithdrawResponse(false, "Amount must be greater than 0", null, null, null);
        }
        
        // Проверяем валюту и достаточность средств
        BigDecimal currentBalance;
        BigDecimal amountByn;
        switch(currency.toLowerCase()) {
            case "byn":
                currentBalance = account.getBalanceByn();
                if(currentBalance.compareTo(amount) < 0){
                    return new WithdrawResponse(false, "Insufficient funds", null, null, null);
                }
                account.setBalanceByn(currentBalance.subtract(amount));
                break;
            case "usd":
                 amountByn = convertUsdToByn(amount);
                currentBalance = account.getBalanceByn();
                if(currentBalance.compareTo(amountByn) < 0){
                    return new WithdrawResponse(false, "Insufficient funds", null, null, null);
                }
                account.setBalanceByn(currentBalance.subtract(amountByn));
                break;
            case "eur":
                amountByn = convertEurToByn(amount);
                currentBalance = account.getBalanceByn();
                if(currentBalance.compareTo(amountByn) < 0){
                    return new WithdrawResponse(false, "Insufficient funds", null, null, null);
                }
                account.setBalanceByn(currentBalance.subtract(amountByn));
                break;
            default:
                return new WithdrawResponse(false, "Invalid currency", null, null, null);
        }
        
        // Сохраняем изменения
        accountRepository.save(account);    
        
        return new WithdrawResponse(true, "Withdrawal successful", 
            account.getBalanceByn(), convertBynToUsd(account.getBalanceByn()), convertBynToEur(account.getBalanceByn()));
    }
        
@Override
public DepositResponse depositMoney(String cardNumber, BigDecimal amount, String currency){

    UserEntity user = userRepository.findUserEntityByCardNumber(cardNumber);
    if(user == null){
        return new DepositResponse(false, "User not found", null, null, null);
    }

    AccountEntity account = accountRepository.findAccountEntityByUserId(user.getUserId());
    if(account == null){
        return new DepositResponse(false, "Account not found", null, null, null);
    }
    
    BigDecimal currentBalance;
    BigDecimal amountByn;

    if(amount.compareTo(BigDecimal.ZERO) <= 0){
        return new DepositResponse(false, "Amount must be greater than 0", null, null, null);
    }

    switch(currency.toLowerCase()) {
        case "byn":
            currentBalance = account.getBalanceByn();
            account.setBalanceByn(currentBalance.add(amount));
            break;
        case "usd":
             amountByn = convertUsdToByn(amount);
            currentBalance = account.getBalanceByn();
            account.setBalanceByn(currentBalance.add(amountByn));
            break;
        case "eur":
            amountByn = convertEurToByn(amount);
            currentBalance = account.getBalanceByn();
            account.setBalanceByn(currentBalance.add(amountByn));
            break;
        default:
            return new DepositResponse(false, "Invalid currency", null, null, null);
    }
    
    
    accountRepository.save(account);
    return new DepositResponse(true, "Deposit successful", account.getBalanceByn(), convertBynToUsd(account.getBalanceByn()), convertBynToEur(account.getBalanceByn()));

}

@Override
public TransferResponse transferMoney(String fromCardNumber, String toCardNumber, BigDecimal amount, String currency){
    
    // Проверяем отправителя
    UserEntity sender = userRepository.findUserEntityByCardNumber(fromCardNumber);
    if(sender == null){
        return new TransferResponse(false, "Sender not found");
    }
    
    // Проверяем получателя
    UserEntity receiver = userRepository.findUserEntityByCardNumber(toCardNumber);
    if(receiver == null){
        return new TransferResponse(false, "Receiver not found");
    }
    
    // Проверяем, что не переводим самому себе
    if(fromCardNumber.equals(toCardNumber)){
        return new TransferResponse(false, "Cannot transfer to yourself");
    }
    
    // Проверяем сумму
    if(amount.compareTo(BigDecimal.ZERO) <= 0){
        return new TransferResponse(false, "Amount must be greater than 0");
    }
    
    // Получаем аккаунты
    AccountEntity senderAccount = accountRepository.findAccountEntityByUserId(sender.getUserId());
    AccountEntity receiverAccount = accountRepository.findAccountEntityByUserId(receiver.getUserId());
    
    if(senderAccount == null || receiverAccount == null){
        return new TransferResponse(false, "Account not found");
    }
    
    // Конвертируем сумму в BYN
    BigDecimal amountByn;
    switch(currency.toLowerCase()) {
        case "byn":
            amountByn = amount;
            break;
        case "usd":
            amountByn = convertUsdToByn(amount);
            break;
        case "eur":
            amountByn = convertEurToByn(amount);
            break;
        default:
            return new TransferResponse(false, "Invalid currency");
    }
    
    // Проверяем достаточность средств
    if(senderAccount.getBalanceByn().compareTo(amountByn) < 0){
        return new TransferResponse(false, "Insufficient funds");
    }
    
    // Выполняем перевод
    senderAccount.setBalanceByn(senderAccount.getBalanceByn().subtract(amountByn));
    receiverAccount.setBalanceByn(receiverAccount.getBalanceByn().add(amountByn));
    
    // Сохраняем изменения
    accountRepository.save(senderAccount);
    accountRepository.save(receiverAccount);
    
    return new TransferResponse(true, "Transfer successful");
}

        
    }


