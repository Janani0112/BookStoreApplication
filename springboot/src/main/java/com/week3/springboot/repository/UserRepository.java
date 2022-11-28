package com.week3.springboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.week3.springboot.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
