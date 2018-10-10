package com.motorola.models.security;

public class AuthenticatorNotFoundException extends RuntimeException {

    public AuthenticatorNotFoundException(String message) {
        super(message);
    }
}
