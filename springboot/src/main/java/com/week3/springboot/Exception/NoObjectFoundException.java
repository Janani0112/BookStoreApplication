package com.week3.springboot.Exception;


public class NoObjectFoundException extends RuntimeException {
    public NoObjectFoundException(String mssg) {
        super(mssg);
    }
}
