package com.bankomat.bankomat.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
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


}
