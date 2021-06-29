package com.swedbank.client.model.response;

import java.util.List;

public class AuthenticationMethodsResponse {

    private List<AuthenticationMethod> authenticationMethods;

    public AuthenticationMethodsResponse() {
    }

    public List<AuthenticationMethod> getAuthenticationMethods() {
        return authenticationMethods;
    }

    public void setAuthenticationMethods(List<AuthenticationMethod> authenticationMethods) {
        this.authenticationMethods = authenticationMethods;
    }

    @Override
    public String toString() {
        return "AuthenticationMethods{" +
                "authenticationMethods=" + authenticationMethods +
                '}';
    }
}

