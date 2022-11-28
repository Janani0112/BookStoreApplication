package com.week3.springboot.service;


import java.util.List;

import com.week3.springboot.model.User;

public interface UserService {
    User createUser(User var1);

    User updateUser(User var1);

    List<User> getUsers();

    User suspendUserById(int var1);
}
