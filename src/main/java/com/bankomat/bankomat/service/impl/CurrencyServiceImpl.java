package com.bankomat.bankomat.service.impl;

import org.springframework.stereotype.Service;

import com.bankomat.bankomat.service.CurrencyService;

import static com.bankomat.bankomat.service.CurrencyRates.*;

import java.math.BigDecimal;
import java.util.Objects;

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
                throw new IllegalArgumentException("Недопустимая валюта");
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
                throw new IllegalArgumentException("Недопустимая валюта");
        }
    }

    @Override
    public void validateCurrency(String currency) {
        if (Objects.isNull(currency) || currency.trim().isEmpty()) {
            throw new IllegalArgumentException("Валюта не может быть пустой");
        }
        
        String lowerCurrency = currency.toLowerCase();
        if (!lowerCurrency.equals("byn") && !lowerCurrency.equals("usd") && !lowerCurrency.equals("eur")) {
            throw new IllegalArgumentException("Недопустимая валюта: " + currency);
        }
    }
}