package com.bankomat.bankomat.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyRates {

    private static final BigDecimal USD_TO_BYN = new BigDecimal("3.00");
    private static final BigDecimal EUR_TO_BYN = new BigDecimal("3.50");


    public static BigDecimal getUsdToBynRate() {
        return USD_TO_BYN;
    }
    
    public static BigDecimal getEurToBynRate() {
        return EUR_TO_BYN;
    }
    
    // Конвертация из USD в BYN
    public static BigDecimal convertUsdToByn(BigDecimal usdAmount) {
        return usdAmount.multiply(USD_TO_BYN);
    }
    
    // Конвертация из EUR в BYN
    public static BigDecimal convertEurToByn(BigDecimal eurAmount) {
        return eurAmount.multiply(EUR_TO_BYN);
    }
    
    // Конвертация из BYN в USD
    public static BigDecimal convertBynToUsd(BigDecimal bynAmount) {
        return bynAmount.divide(USD_TO_BYN, 2, RoundingMode.HALF_UP);
    }
    
    // Конвертация из BYN в EUR
    public static BigDecimal convertBynToEur(BigDecimal bynAmount) {
        return bynAmount.divide(EUR_TO_BYN, 2, RoundingMode.HALF_UP);
    }


}
