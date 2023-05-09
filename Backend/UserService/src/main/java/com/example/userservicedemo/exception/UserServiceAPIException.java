package com.example.userservicedemo.exception;

public class UserServiceAPIException extends RuntimeException{
    private String message;

    public UserServiceAPIException() {
    }

    public UserServiceAPIException(String message) {
        super(message);
        this.message = message;
    }
}

