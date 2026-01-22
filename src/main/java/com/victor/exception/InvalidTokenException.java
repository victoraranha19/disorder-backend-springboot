package com.victor.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String token) {
        super("Token inválido. Token: " + token);
    }
}
