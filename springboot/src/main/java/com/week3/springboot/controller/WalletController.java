package com.week3.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.week3.springboot.model.User;
import com.week3.springboot.model.Wallet;
import com.week3.springboot.service.WalletService;

@RestController
public class WalletController {
    @Autowired
    private WalletService walletService;

    public WalletController() {
    }

   /* 
    @PostMapping({"/adduser"})
    private ResponseEntity<User> saveUser(@RequestBody Wallet wallet) {
            return ResponseEntity.ok().body(this.userService.createUser(user));
    }*/

    @PutMapping({"/users/addwallet/{walletId}"})
    private ResponseEntity<Wallet> updateUser(@PathVariable int walletId, @RequestBody Wallet wallet) {
        wallet.setWalletId(walletId);
        return ResponseEntity.ok().body(this.walletService.addAmount(wallet));
    }
}
