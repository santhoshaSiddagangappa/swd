package com.swedbank.client;

import com.swedbank.client.authenticators.AuthenticatorNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ApiClientJavaApplicationTests {

	AuthenticationService authenticationService = new AuthenticationService();

	@Test
	public void testApplication() throws InterruptedException, AuthenticatorNotFoundException, IOException {

		authenticationService.authenticationMethods();
		boolean isAuthentic = authenticationService.authenticate("19121212-1212");
		Assertions.assertEquals(isAuthentic, false);
	}

}
