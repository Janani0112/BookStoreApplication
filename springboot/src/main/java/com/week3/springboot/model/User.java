package com.week3.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
    name = "user"
)
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(
        strategy = GenerationType.AUTO
    )
    private Integer userId;
    @Column(
        name = "user_name"
    )
    private String userName;
    @Column(
        name = "user_email"
    )
    private String userEmail;
    @Column(
        name = "user_phone"
    )
    private long userPhNo;
    @Column(
        name = "user_status"
    )
    private boolean userStatus;

    public User() {
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public long getUserPhNo() {
        return this.userPhNo;
    }

    public void setUserPhNo(long userPhNo) {
        this.userPhNo = userPhNo;
    }

    public boolean isUserStatus() {
        return this.userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }
}