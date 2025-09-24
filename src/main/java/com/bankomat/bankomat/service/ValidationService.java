package com.bankomat.bankomat.service;

import com.bankomat.bankomat.entity.AccountEntity;
import com.bankomat.bankomat.entity.ServiceEntity;
import com.bankomat.bankomat.entity.UserEntity;
import com.bankomat.bankomat.exception.AccountNotFoundException;
import com.bankomat.bankomat.exception.UserNotFoundException;

import java.util.Objects;

import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public ValidationService(){
    }

    public void  validationUser(UserEntity user){

       if(Objects.isNull(user)){
            throw new UserNotFoundException("Пользователь не найден");  
        }

        if(user.getUserId() == null){
            throw new IllegalArgumentException("ID пользователя не может быть пустым");
        }
    }

    public void  validationAccount(AccountEntity account){
        
        if(Objects.isNull(account)){
            throw new AccountNotFoundException("Аккаунт не найден");
        }
 
        if(account.getAccountId() == null){
            throw new IllegalArgumentException("ID аккаунта не может быть пустым");
        }
     }


     public void  validationService(ServiceEntity service){

        if(Objects.isNull(service)){
            throw new IllegalArgumentException("Услуга не найдена или неактивна");
        }
 
    
     }
    

}
