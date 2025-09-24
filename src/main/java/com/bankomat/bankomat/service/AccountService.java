package com.bankomat.bankomat.service;

import com.bankomat.bankomat.entity.AccountEntity;
import java.math.BigDecimal;

public interface AccountService {
    void withdraw(AccountEntity account, BigDecimal amount, String currency);
    void deposit(AccountEntity account, BigDecimal amount, String currency);
    void transfer(AccountEntity fromAccount, AccountEntity toAccount, BigDecimal amount, String currency);
}