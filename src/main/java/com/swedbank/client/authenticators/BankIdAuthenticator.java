package com.swedbank.client.authenticators;

import com.swedbank.client.Constants;
import com.swedbank.client.model.HttpHelper;
import com.swedbank.client.model.request.MobileBankIdRequest;
import com.swedbank.client.model.response.AbstractAuthenticationResponse;
import com.swedbank.client.model.response.AuthenticationMethod;
import com.swedbank.client.model.response.BankIdAuthenticationResponse;

import java.io.IOException;
import java.net.http.HttpResponse;

import static com.swedbank.client.AuthenticationService.getHeaderMap;
import static com.swedbank.client.AuthenticationService.gson;

public class BankIdAuthenticator extends AbstractAuthenticator {

    static int verifyCount = 1;

    @Override
    public boolean authenticate(AuthenticationMethod authenticationMethod, String bankId) throws IOException, InterruptedException {

        System.out.println("\nStarting Mobile BankID Authentication");

        String uri = authenticationMethod.getLocation().getUri();
        HttpResponse<String> response = HttpHelper.post(uri, getHeaderMap(), gson.toJson(new MobileBankIdRequest(bankId)));

        BankIdAuthenticationResponse bankIdAuthenticationResponse = gson.fromJson(response.body(), BankIdAuthenticationResponse.class);
        //System.out.println(bankIdAuthenticationResponse.toString());

        System.out.println("\nStarting Mobile BankID Authentication");

        boolean isValid = false;
        do{
            System.out.println("\nPerforming verification number - " + verifyCount);
            bankIdAuthenticationResponse = (BankIdAuthenticationResponse) next(bankIdAuthenticationResponse);

            if(bankIdAuthenticationResponse.getStatus().equalsIgnoreCase(Constants.BANK_ID_SUCCESS_CODE)){
                System.out.println("\nVerification Successful for bankId - "
                        + bankIdAuthenticationResponse.getMaskedUserId() + " at "
                        + bankIdAuthenticationResponse.getServerTime());

                System.out.println("\nRedirecting to - "
                        + bankIdAuthenticationResponse.getLinks().getNext().getUri());

                isValid = true;
            }
            else {
                System.out.println("\nVerification status - "
                        + bankIdAuthenticationResponse.getStatus() + " at "
                        + bankIdAuthenticationResponse.getServerTime());

                if(verifyCount<Constants.MAX_VERIFY_COUNT)
                    System.out.println("\nTrying after 1 second");
                Thread.sleep(Constants.VERIFY_INTERVAL_MILLIS);
            }

        }while(verifyCount<=Constants.MAX_VERIFY_COUNT && isValid == false);

        return isValid;
    }

    @Override
    public AbstractAuthenticationResponse next(AbstractAuthenticationResponse bankIdResponse) throws IOException, InterruptedException {
        verifyCount++;

        String uri = ((BankIdAuthenticationResponse) bankIdResponse).getLinks().getNext().getUri();
        HttpResponse<String> response = HttpHelper.get(uri, getHeaderMap());

        BankIdAuthenticationResponse verificationResponse = gson.fromJson(response.body(), BankIdAuthenticationResponse.class);
        //System.out.println(verificationResponse.toString());

        return verificationResponse;
    }

}
