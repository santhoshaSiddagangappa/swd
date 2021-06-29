package com.swedbank.client.authenticators;

import com.swedbank.client.model.response.AbstractAuthenticationResponse;
import com.swedbank.client.model.response.AuthenticationMethod;

import java.io.IOException;

public abstract class AbstractAuthenticator {

    public abstract boolean authenticate(AuthenticationMethod authenticationMethod, String bankId) throws IOException, InterruptedException;

    public abstract AbstractAuthenticationResponse next(AbstractAuthenticationResponse response) throws IOException, InterruptedException;
}
