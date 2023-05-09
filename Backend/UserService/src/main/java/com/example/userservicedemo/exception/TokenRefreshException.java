package com.example.userservicedemo.exception;

public class TokenRefreshException extends RuntimeException{
    private String message;

    public TokenRefreshException() {
    }

    public TokenRefreshException(String message) {
        super(message);
        this.message = message;
    }
}
