package com.swedbank.client.authenticators;

public class AuthenticatorNotFoundException extends Throwable {

    public AuthenticatorNotFoundException(String message) {
        super(message);
    }
}
