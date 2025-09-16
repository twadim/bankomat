package com.bankomat.bankomat.response;

import lombok.Data;

import java.math.BigDecimal;
import static com.bankomat.bankomat.services.CurrencyRates.*;
@Data
public class UserResponse {

    private String userName;
    private BigDecimal balanceByn;
    private BigDecimal balanceUsd;
    private BigDecimal balanceEur;


}
