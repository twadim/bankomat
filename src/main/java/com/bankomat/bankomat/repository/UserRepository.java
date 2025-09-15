package com.bankomat.bankomat.repository;

import com.bankomat.bankomat.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

 UserEntity findUserEntityByCardNumberAndPinCode(String cardNumber, String pinCode);
 UserEntity findUserEntityByCardNumber(String cardNumber);

}
