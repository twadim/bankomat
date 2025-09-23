package com.bankomat.bankomat.repository;

import com.bankomat.bankomat.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    ServiceEntity findServiceEntityByServiceCode(String serviceCode);
    ServiceEntity findServiceEntityByServiceCodeAndIsActive(String serviceCode, Boolean isActive);

}


