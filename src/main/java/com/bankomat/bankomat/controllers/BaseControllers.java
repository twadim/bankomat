package com.bankomat.bankomat.controllers;

import com.bankomat.bankomat.request.UserAuthRequest;
import com.bankomat.bankomat.response.UserResponse;
import com.bankomat.bankomat.services.impl.AtmServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BaseControllers {

    private final AtmServiceImpl atmServiceImpl;

    public BaseControllers(AtmServiceImpl atmServiceImpl) {
        this.atmServiceImpl = atmServiceImpl;
    }



    @PostMapping(value = "/auth")
    public ResponseEntity<UserResponse> AuthUser(@RequestBody UserAuthRequest userAuthRequest) {


        UserResponse userResponse = atmServiceImpl.userAuth(userAuthRequest.getCardNumber(),userAuthRequest.getPassword());

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

}
