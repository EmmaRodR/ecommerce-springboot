package com.ecommercespringboot.exceptions.especificExceptions;


public class TokenValidationException extends RuntimeException {

    public TokenValidationException (String message, Throwable cause) {
        super (message,cause);
    }

    
}