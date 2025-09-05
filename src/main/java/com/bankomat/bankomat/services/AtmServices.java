package com.bankomat.bankomat.services;

import com.bankomat.bankomat.response.UserResponse;

public interface AtmServices {


    UserResponse userAuth(String cardNumber, String password);

}
