package com.week3.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(
    name = "wallet"
)
public class Wallet {
    @Id
    @Column(
        name = "wallet_id"
    )
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int walletId;
    @Column(
        name = "user_id"
    )
    private int userId;
    @Column(
        name = "balance"
    )
    private int balance;

    public Wallet() {
    }

    public int getWalletId() {
        return this.walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setDefaultBalance() {
        this.balance = 500;
    }

    public void addAmount(int amount) {
        this.balance += amount;
    }
}
