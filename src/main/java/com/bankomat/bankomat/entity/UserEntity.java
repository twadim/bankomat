package com.bankomat.bankomat.entity;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class UserEntity  {

    public UserEntity(){

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @Column(name="name")
    private String userName;

    @Column(name="card")
    private String cardNumber;

    @Column(name="password")
    private String pinCode;

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", pinCode='" + pinCode + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
}
