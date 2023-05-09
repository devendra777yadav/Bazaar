package com.example.cartservice.exception;

public class UserNotExistException extends RuntimeException{
    private String message;

    public UserNotExistException() {}

    public UserNotExistException(String message) {
        super(message);
        this.message = message;
    }
}
