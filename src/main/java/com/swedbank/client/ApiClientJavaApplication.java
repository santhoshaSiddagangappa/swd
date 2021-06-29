package com.swedbank.client;

import com.swedbank.client.authenticators.AuthenticatorNotFoundException;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ApiClientJavaApplication {


	public static void main(String[] args) throws IOException, AuthenticatorNotFoundException, InterruptedException {

		System.out.println("Starting SwedBank Authentication Client");

		AuthenticationService authenticationService = new AuthenticationService();

		authenticationService.authenticationMethods();

		//reading bankid from stdin
		String mobileBankId = readBankId();
		//String mobileBankId = "19121212-1212";

		System.out.println("\nStarting Mobile Bank id authentication for - " + mobileBankId);

		boolean isAuthenticUser = authenticationService.authenticate(mobileBankId);

		if(isAuthenticUser)
			System.out.println("\nUser Authenticated. Welcome to your account!");
		else
			System.out.println("\nUser Authentication Failed. The police has been notified.");

	}

	private static String readBankId() {
		boolean invalidBankId = true;
		String bankId = null;

		//some basic validation
		do{
			System.out.println("\nEnter Bank-id (yyyymmdd-xxxx) - ");
			Scanner in = new Scanner(System.in);
			bankId = in.nextLine();
			if(Pattern.matches(Constants.BANK_ID_PATTERN, bankId)) {
				invalidBankId = false;
				in.close();
			}
			else
				System.out.println("Please enter a valid bank id");
		}while(invalidBankId);

		return bankId;
	}

}
