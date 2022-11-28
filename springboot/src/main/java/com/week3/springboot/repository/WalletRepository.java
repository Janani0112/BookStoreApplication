package com.week3.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.week3.springboot.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
}
