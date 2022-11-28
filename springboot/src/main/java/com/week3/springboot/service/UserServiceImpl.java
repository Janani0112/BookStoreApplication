package com.week3.springboot.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.week3.springboot.Exception.NoObjectFoundException;
import com.week3.springboot.model.User;
import com.week3.springboot.model.Wallet;
import com.week3.springboot.repository.UserRepository;
import com.week3.springboot.repository.WalletRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userrepo;
    @Autowired
    private WalletRepository walletrepo;
    @Autowired
    private Wallet wallet;

    public UserServiceImpl() {
    }

    public User createUser(User user) {
        user.setUserStatus(true);
        User userAdded = (User)this.userrepo.save(user);
        this.wallet.setDefaultBalance();
        this.wallet.setUserId(user.getUserId());
        this.walletrepo.save(this.wallet);
        return userAdded;
    }

    public User updateUser(User user) {
        Optional<User> userObj = this.userrepo.findById(user.getUserId());
        if (userObj.isPresent()) {
            User userUpdate = (User)userObj.get();
            userUpdate.setUserId(user.getUserId());
            userUpdate.setUserName(user.getUserName());
            userUpdate.setUserEmail(user.getUserEmail());
            userUpdate.setUserPhNo(user.getUserPhNo());
            this.userrepo.save(userUpdate);
            return userUpdate;
        } else {
            throw new NoObjectFoundException("User ID doesnot exist: " + user.getUserId());
        }
    }

    public List<User> getUsers() {
        return this.userrepo.findAll();
    }

    public User suspendUserById(int userid) {
        Optional<User> userObj = this.userrepo.findById(userid);
        if (userObj.isPresent()) {
            User userSuspend = (User)userObj.get();
            userSuspend.setUserStatus(false);
            this.userrepo.save(userSuspend);
            return userSuspend;
        } else {
            throw new NoObjectFoundException("User ID doesnot exist: " + userid);
        }
    }
}
