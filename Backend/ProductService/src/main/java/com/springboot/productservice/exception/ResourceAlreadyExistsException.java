package com.springboot.productservice.exception;

public class ResourceAlreadyExistsException extends RuntimeException{
    private String message;
    public ResourceAlreadyExistsException(){
    }

    public ResourceAlreadyExistsException(String message){
        super(message);
        this.message = message;
    }
}
