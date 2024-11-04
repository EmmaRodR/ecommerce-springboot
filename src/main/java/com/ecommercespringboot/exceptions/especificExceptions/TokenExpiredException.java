package com.ecommercespringboot.exceptions.especificExceptions;


public class TokenExpiredException extends Exception {

    public TokenExpiredException (String msg, Throwable cause) {
        super(msg,cause);
    }

}