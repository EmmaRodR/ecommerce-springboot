package com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions;


public class TokenExpiredException extends Exception {

    public TokenExpiredException (String msg, Throwable cause) {
        super(msg,cause);
    }

}