package com.bankomat.bankomat.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Entity
@Table(name="accounts")
public class AccountEntity {

    public AccountEntity(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long accountId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="balance_byn")
    private BigDecimal balanceByn;

    @Column(name="balance_usd")
    private BigDecimal balanceUsd;

    @Column(name="balance_eur")
    private BigDecimal balanceEur;

}
