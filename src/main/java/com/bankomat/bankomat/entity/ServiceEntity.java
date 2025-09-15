package com.bankomat.bankomat.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="services")
public class ServiceEntity {

    public ServiceEntity(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long serviceId;

    @Column(name ="code", unique = true, nullable = false)
    private String serviceCode;

    @Column(name="name", nullable = false)
    private String serviceName;

    @Column(name="description")
    private String serviceDescription;

    @Column(name="price", nullable = false)
    private BigDecimal priceByn;

    @Column(name="active")
    private Boolean isActive;

    @Override
    public String toString() {
        return "ServiceEntity{" +
                "serviceId=" + serviceId +
                ", serviceCode=" + serviceCode +
                ", serviceName=" + serviceName +
                ", serviceDescription=" + serviceDescription +
                ", priceByn=" + priceByn +
                ", isActive=" + isActive +
                '}';
    }

    public Long getServiceId() {
        return serviceId;
    }
    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public BigDecimal getPriceByn() {
        return priceByn;
    }

    public void setPriceByn(BigDecimal priceByn) {
        this.priceByn = priceByn;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
   


}   
