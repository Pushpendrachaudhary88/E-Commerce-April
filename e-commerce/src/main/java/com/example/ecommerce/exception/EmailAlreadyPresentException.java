package com.example.ecommerce.exception;

public class EmailAlreadyPresentException extends Throwable {
    public EmailAlreadyPresentException(String message){
        super(message);
    }

}
