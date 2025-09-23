package com.bankomat.bankomat.services.impl;

import com.bankomat.bankomat.entity.AccountEntity;
import com.bankomat.bankomat.exception.InsufficientFundsException;
import com.bankomat.bankomat.services.AccountService;
import com.bankomat.bankomat.services.CurrencyService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final CurrencyService currencyService;

    public AccountServiceImpl(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public void withdraw(AccountEntity account, BigDecimal amount, String currency) {
       
        BigDecimal amountByn = currencyService.convertToByn(amount, currency);
        BigDecimal currentBalance = account.getBalanceByn();

        if (currentBalance.compareTo(amountByn) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        account.setBalanceByn(currentBalance.subtract(amountByn));
    }

    @Override
    public void deposit(AccountEntity account, BigDecimal amount, String currency) {
      
        BigDecimal amountByn = currencyService.convertToByn(amount, currency);
        BigDecimal currentBalance = account.getBalanceByn();

        account.setBalanceByn(currentBalance.add(amountByn));
    }

    @Override
    public void transfer(AccountEntity fromAccount, AccountEntity toAccount, BigDecimal amount, String currency) {
    

        BigDecimal amountByn = currencyService.convertToByn(amount, currency);
        BigDecimal fromBalance = fromAccount.getBalanceByn();

        if (fromBalance.compareTo(amountByn) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        fromAccount.setBalanceByn(fromBalance.subtract(amountByn));
        toAccount.setBalanceByn(toAccount.getBalanceByn().add(amountByn));
    }
}