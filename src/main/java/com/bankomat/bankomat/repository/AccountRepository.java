package com.bankomat.bankomat.repository;

import com.bankomat.bankomat.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    AccountEntity findAccountEntityByUserId(Long userId);

}
