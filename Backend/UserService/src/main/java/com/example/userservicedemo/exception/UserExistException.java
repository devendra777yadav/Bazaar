package com.example.userservicedemo.exception;

public class UserExistException extends RuntimeException{
    private String message;

    public UserExistException() {
    }

    public UserExistException(String message) {
        super(message);
        this.message = message;
    }
}
