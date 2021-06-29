package com.swedbank.client.authenticators;

import com.swedbank.client.Constants;

public class AuthenticatorFactory {

    static BankIdAuthenticator bankIdAuthenticator = new BankIdAuthenticator();

    public static AbstractAuthenticator getAuthenticator(String authenticationMethod) throws AuthenticatorNotFoundException {
        if(authenticationMethod.equals(Constants.AUTHENTICATOR_MOBILE_BANKID))
            return bankIdAuthenticator;
        else
            throw new AuthenticatorNotFoundException("Authentication Method not supported");
    }
}
