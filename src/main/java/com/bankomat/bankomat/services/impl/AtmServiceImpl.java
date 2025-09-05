package com.bankomat.bankomat.services.impl;

import com.bankomat.bankomat.entity.UserEntity;
import com.bankomat.bankomat.repository.UserRepository;
import com.bankomat.bankomat.response.UserResponse;
import com.bankomat.bankomat.services.AtmServices;
import org.springframework.stereotype.Service;

@Service
public class AtmServiceImpl implements AtmServices {


    private final UserRepository userRepository;

    public AtmServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse userAuth(String cardNumber, String password) {

        UserResponse userResponse = new UserResponse();

        UserEntity user = userRepository.findUserEntityByCardNumberAndPinCode(cardNumber, password);

        userResponse.setUserName(user.getUserName());

        return userResponse;

    }



}
