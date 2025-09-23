package com.bankomat.bankomat.services.impl;

import com.bankomat.bankomat.services.CurrencyService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.bankomat.bankomat.services.CurrencyRates.*;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Override
    public BigDecimal convertToByn(BigDecimal amount, String currency) {
        validateCurrency(currency);
        
        switch(currency.toLowerCase()) {
            case "byn":
                return amount;
            case "usd":
                return convertUsdToByn(amount);
            case "eur":
                return convertEurToByn(amount);
            default:
                throw new IllegalArgumentException("Invalid currency");
        }
    }

    @Override
    public BigDecimal convertFromByn(BigDecimal bynAmount, String currency) {
        validateCurrency(currency);
        
        switch(currency.toLowerCase()) {
            case "byn":
                return bynAmount;
            case "usd":
                return convertBynToUsd(bynAmount);
            case "eur":
                return convertBynToEur(bynAmount);
            default:
                throw new IllegalArgumentException("Invalid currency");
        }
    }

    @Override
    public void validateCurrency(String currency) {
        if (currency == null || currency.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency cannot be null or empty");
        }
        
        String lowerCurrency = currency.toLowerCase();
        if (!lowerCurrency.equals("byn") && !lowerCurrency.equals("usd") && !lowerCurrency.equals("eur")) {
            throw new IllegalArgumentException("Invalid currency: " + currency);
        }
    }
}