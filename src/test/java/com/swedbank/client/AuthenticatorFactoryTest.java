package com.swedbank.client;

import com.swedbank.client.authenticators.AbstractAuthenticator;
import com.swedbank.client.authenticators.AuthenticatorFactory;
import com.swedbank.client.authenticators.AuthenticatorNotFoundException;
import com.swedbank.client.authenticators.BankIdAuthenticator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthenticatorFactoryTest {

    @Test
    public void testFactoryPass() throws AuthenticatorNotFoundException {

        AbstractAuthenticator authenticator = AuthenticatorFactory.getAuthenticator(Constants.AUTHENTICATOR_MOBILE_BANKID);
        Assertions.assertEquals(authenticator.getClass(), BankIdAuthenticator.class);
    }

    @Test
    public void testFactoryFail(){

        Assertions.assertThrows(AuthenticatorNotFoundException.class
                , ()-> AuthenticatorFactory.getAuthenticator("RANDOM_TEXT"));
    }
}
