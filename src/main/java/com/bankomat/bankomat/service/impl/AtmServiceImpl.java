package com.bankomat.bankomat.service.impl;

import com.bankomat.bankomat.entity.AccountEntity;
import com.bankomat.bankomat.entity.ServiceEntity;
import com.bankomat.bankomat.entity.UserEntity;
import com.bankomat.bankomat.exception.InsufficientFundsException;
import com.bankomat.bankomat.repository.AccountRepository;
import com.bankomat.bankomat.repository.ServiceRepository;
import com.bankomat.bankomat.repository.UserRepository;
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
import com.bankomat.bankomat.service.AccountService;
import com.bankomat.bankomat.service.AtmServices;
import com.bankomat.bankomat.service.ValidationService;

import static com.bankomat.bankomat.service.CurrencyRates.*;

import java.math.BigDecimal;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AtmServiceImpl implements AtmServices {


    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final ValidationService validationService;
    private final ServiceRepository serviceRepository;
    private final AccountService accountService;


    public AtmServiceImpl(UserRepository userRepository, AccountRepository accountRepository, ServiceRepository serviceRepository, ValidationService validationService, AccountService accountService) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.serviceRepository = serviceRepository;
        this.validationService = validationService;
        this.accountService = accountService;

    }

    @Transactional
    @Override
    public PaymentResponse payForService(PaymentRequest paymentRequest) {

        String cardNumber = paymentRequest.getCardNumber();
        String serviceCode = paymentRequest.getServiceCode();

        UserEntity user = userRepository.findUserEntityByCardNumber(cardNumber);
        validationService.validationUser(user);

        AccountEntity account = accountRepository.findAccountEntityByUserId(user.getUserId());
        validationService.validationAccount(account);

        ServiceEntity service = serviceRepository.findServiceEntityByServiceCodeAndIsActive(serviceCode, true);
        validationService.validationService(service);


        BigDecimal currentBalance = account.getBalanceByn();
        BigDecimal servicePrice = service.getPriceByn();

        if(currentBalance.compareTo(servicePrice) < 0){
            throw new InsufficientFundsException("Недостаточно средств"); 
           
        }
        else {

            account.setBalanceByn(currentBalance.subtract(servicePrice));
            accountRepository.save(account);
            return new PaymentResponse(true, "Оплата выполнена", service.getServiceName(), servicePrice, account.getBalanceByn(), convertBynToUsd(account.getBalanceByn()), convertBynToEur(account.getBalanceByn()));
        }

    }

    @Override
    public UserResponse userAuth(UserAuthRequest userAuthRequest) {

        String cardNumber = userAuthRequest.getCardNumber();
        String password = userAuthRequest.getPassword();

        UserResponse userResponse = new UserResponse();

        UserEntity user = userRepository.findUserEntityByCardNumberAndPinCode(cardNumber, password);
        validationService.validationUser(user);

        AccountEntity account = accountRepository.findAccountEntityByUserId(user.getUserId());
        validationService.validationAccount(account);


        userResponse.setUserName(user.getUserName());
        userResponse.setBalanceByn(account.getBalanceByn());
        userResponse.setBalanceUsd(convertBynToUsd(account.getBalanceByn()));
        userResponse.setBalanceEur(convertBynToEur(account.getBalanceByn()));

        return userResponse;

    }

    @Override
    public BalanceResponse getBalance(BalanceRequest balanceRequest) {

        String cardNumber = balanceRequest.getCardNumber();

        UserEntity user = userRepository.findUserEntityByCardNumber(cardNumber);
        validationService.validationUser(user);
        

        AccountEntity account = accountRepository.findAccountEntityByUserId(user.getUserId());
        validationService.validationAccount(account);

        return new BalanceResponse("Balance user "+user.getUserName(), account.getBalanceByn(), convertBynToUsd(account.getBalanceByn()), convertBynToEur(account.getBalanceByn()));
    }

    @Transactional
    @Override
    public WithdrawResponse withdrawMoney(WithdrawRequest withdrawRequest){  

        String cardNumber = withdrawRequest.getCardNumber();
        BigDecimal amount = withdrawRequest.getAmount();
        String currency = withdrawRequest.getCurrency();

        // Проверяем пользователя
        UserEntity user = userRepository.findUserEntityByCardNumber(cardNumber);
        validationService.validationUser(user);
        
        // Проверяем аккаунт
        AccountEntity account = accountRepository.findAccountEntityByUserId(user.getUserId());
        validationService.validationAccount(account);
        

        accountService.withdraw(account, amount, currency);
        // Сохраняем изменения
        accountRepository.save(account);    
        
        return new WithdrawResponse(true, "Снятие выполнено успешно", 
            account.getBalanceByn(), convertBynToUsd(account.getBalanceByn()), convertBynToEur(account.getBalanceByn()));
    }

    @Transactional
    @Override
public DepositResponse depositMoney(DepositRequest depositRequest){

    String cardNumber = depositRequest.getCardNumber();
    BigDecimal amount = depositRequest.getAmount();
    String currency = depositRequest.getCurrency();

    UserEntity user = userRepository.findUserEntityByCardNumber(cardNumber);
    validationService.validationUser(user);

    AccountEntity account = accountRepository.findAccountEntityByUserId(user.getUserId());
    validationService.validationAccount(account);

    accountService.deposit(account, amount, currency);
   
    accountRepository.save(account);
    return new DepositResponse(true, "Пополнение выполнено успешно", account.getBalanceByn(), convertBynToUsd(account.getBalanceByn()), convertBynToEur(account.getBalanceByn()));

}

    @Transactional
public TransferResponse transferMoney(TransferRequest transferRequest){

    String fromCardNumber = transferRequest.getFromCardNumber();
    String toCardNumber = transferRequest.getToCardNumber();
    BigDecimal amount = transferRequest.getAmount();
    String currency = transferRequest.getCurrency();

    // Проверяем отправителя
    UserEntity sender = userRepository.findUserEntityByCardNumber(fromCardNumber);
    validationService.validationUser(sender);
    
    // Проверяем получателя
    UserEntity receiver = userRepository.findUserEntityByCardNumber(toCardNumber);
    validationService.validationUser(receiver);

    
    // Проверяем, что не переводим самому себе
    if(fromCardNumber.equals(toCardNumber)){
        throw new IllegalArgumentException("Нельзя переводить деньги самому себе");
      
    }
    
    // Получаем аккаунты
    AccountEntity senderAccount = accountRepository.findAccountEntityByUserId(sender.getUserId());
    validationService.validationAccount(senderAccount);

    AccountEntity receiverAccount = accountRepository.findAccountEntityByUserId(receiver.getUserId());
    validationService.validationAccount(receiverAccount);

    accountService.transfer(senderAccount, receiverAccount, amount, currency);

   
    
    // Сохраняем изменения
    accountRepository.save(senderAccount);
    accountRepository.save(receiverAccount);
    
    return new TransferResponse(true, "Перевод выполнен успешно");
}

        
    }


