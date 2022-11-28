package com.week3.springboot.service;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.week3.springboot.Exception.NoObjectFoundException;
import com.week3.springboot.model.Wallet;
import com.week3.springboot.repository.WalletRepository;

@Service
@Transactional
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletRepository walletrepo;

    public WalletServiceImpl() {
    }

    public Wallet addAmount(Wallet wallet) {
        Optional<Wallet> walletObj = this.walletrepo.findById(wallet.getWalletId());
        if (walletObj.isPresent() && wallet.getBalance() > 0 && wallet.getBalance() % 500 == 0) {
            Wallet walletUpdate = (Wallet)walletObj.get();
            walletUpdate.setBalance(walletUpdate.getBalance() + wallet.getBalance());
            this.walletrepo.save(walletUpdate);
            return walletUpdate;
        } else {
            throw new NoObjectFoundException("Enter valid fields");
        }
    }

    public void updateWallet(Wallet wallet) {
        Optional<Wallet> walletObj = this.walletrepo.findById(wallet.getUserId());
        if (walletObj.isPresent()) {
            Wallet walletUpdate = (Wallet)walletObj.get();
            walletUpdate.setBalance(wallet.getBalance());
            this.walletrepo.save(walletUpdate);
        } else {
            throw new NoObjectFoundException("No wallet with the given ID " + wallet.getWalletId());
        }
    }
}
