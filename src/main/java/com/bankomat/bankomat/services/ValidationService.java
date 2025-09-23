package com.bankomat.bankomat.services;

import com.bankomat.bankomat.entity.AccountEntity;
import com.bankomat.bankomat.entity.ServiceEntity;
import com.bankomat.bankomat.entity.UserEntity;
import com.bankomat.bankomat.exception.AccountNotFoundException;
import com.bankomat.bankomat.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public ValidationService(){
    }

    public void  validationUser(UserEntity user){

       if(user == null){
            throw new UserNotFoundException("User not found");  
        }

        if(user.getUserId() == null){
            throw new IllegalArgumentException("User ID cannot be null");
        }
    }

    public void  validationAccount(AccountEntity account){

        if(account == null){
            throw new AccountNotFoundException("Account not found");
        }
 
        if(account.getAccountId() == null){
            throw new IllegalArgumentException("Account ID cannot be null");
        }
     }


     public void  validationService(ServiceEntity service){

        if(service == null){
            throw new IllegalArgumentException("Service not found or inactive");
        }
 
    
     }
    

}
