package com.bankomat.bankomat.services;

import java.math.BigDecimal;

public interface CurrencyService {
    BigDecimal convertToByn(BigDecimal amount, String currency);
    BigDecimal convertFromByn(BigDecimal bynAmount, String currency);
    void validateCurrency(String currency);
}