package com.bankomat.bankomat.request;

import lombok.Data;

@Data
public class UserAuthRequest
{

    private String cardNumber;
    private String password;


}
