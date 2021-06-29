package com.swedbank.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.swedbank.client.authenticators.AbstractAuthenticator;
import com.swedbank.client.authenticators.AuthenticatorFactory;
import com.swedbank.client.authenticators.AuthenticatorNotFoundException;
import com.swedbank.client.model.HttpHelper;
import com.swedbank.client.model.response.AuthenticationMethod;
import com.swedbank.client.model.response.AuthenticationMethodsResponse;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AuthenticationService {

    public static String jSessionID = null;
    public static Optional<String> cookieString = null;
    public static AuthenticationMethodsResponse authenticationMethods = null;
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public void authenticationMethods() throws IOException, InterruptedException {
        //Session Cookies
        clientSession();
        System.out.println("\nSession created with ID - " + jSessionID);

        getAuthenticationMethods();
        printAuthenticationMethods();
    }

    private void getAuthenticationMethods() throws IOException, InterruptedException {
        System.out.println("\nFetching Authentication Methods");

        HttpResponse<String> response = HttpHelper.get(Constants.IDENTIFICATION_ENDPOINT_V5, getHeaderMap());
        authenticationMethods = gson.fromJson(response.body(), AuthenticationMethodsResponse.class);
        //System.out.println(authenticationMethods.toString());
    }


    private void printAuthenticationMethods() {
        int counter = 1;
        System.out.println("\nAvailable Authentication Methods");
        System.out.println("-------------------------------------------------------");
        for(AuthenticationMethod authenticationMethod: authenticationMethods.getAuthenticationMethods()) {
            System.out.println(counter + ".\t" + authenticationMethod.getMessage());
            counter++;
        }
        System.out.println("-------------------------------------------------------");

    }

    public boolean authenticate(String mobileBankId) throws IOException, InterruptedException, AuthenticatorNotFoundException {
        //mobile bankid authentication
        return authenticate(Constants.AUTHENTICATOR_MOBILE_BANKID, mobileBankId);
    }

    private void clientSession() throws IOException, InterruptedException {

        System.out.println("\nFetching Session Cookie");

        Map<String, String> initHeaderMap = new HashMap<>();
        initHeaderMap.put("Authorization", Constants.HEADER_AUTH_KEY);
        HttpResponse<String> response = HttpHelper.get(Constants.CLIENT_SESSION_ENDPOINT_V5, initHeaderMap);

        Map<String, List<String>> headerMap = getHeaderMap(response.headers());
        Map<String, String> cookieMap = constructCookieMap(headerMap);

        jSessionID = cookieMap.get("JSESSIONID");
        cookieString = Optional.of(constructCookieString(cookieMap));

    }

    private Map<String, List<String>> getHeaderMap(HttpHeaders headers) {
        Map<String, List<String>> headerMap;
        headerMap = headers.map().entrySet().stream()
                .filter(entry -> entry.getKey() != null)
                .collect(Collectors.toMap(e->e.getKey(), e->e.getValue()));
        return headerMap;
    }


    public Map<String, String> constructCookieMap(Map<String, List<String>> headerMap) {
        List<String> set_cookie = headerMap.get("set-cookie");
        return set_cookie.stream()
                .collect(Collectors.toMap(
                        s -> s.split(";")[0].split("=")[0],
                        s -> s.split(";")[0].split("=")[1]));
    }


    private String constructCookieString(Map<String, String> cookieMap) {
        return cookieMap.entrySet()
                .stream()
                .map(entry -> entry.getKey()+"="+entry.getValue())
                .collect(Collectors.joining(";"))+";";
    }

    private boolean authenticate(String authenticatorType, String mobileBankId) throws AuthenticatorNotFoundException, IOException, InterruptedException {

        if( authenticationMethods==null || cookieString == null || jSessionID == null )
            authenticationMethods();

        Optional<AuthenticationMethod> authenticationMethod = authenticationMethods.getAuthenticationMethods()
                .stream().filter(a -> a.getCode().equals(authenticatorType)).findFirst();

        if(authenticationMethod.isEmpty())
            throw new AuthenticatorNotFoundException("Authentication method not found");

        AbstractAuthenticator authenticator = AuthenticatorFactory.getAuthenticator(authenticatorType);
        return authenticator.authenticate(authenticationMethod.get(), mobileBankId);
    }


    public static Map<String, String> getHeaderMap() {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", Constants.HEADER_AUTH_KEY);
        headerMap.put("User-Agent", Constants.HEADER_AGENT);
        //headerMap.put("Host", Constants.HEADER_HOST);
        headerMap.put("Sec-Fetch-Mode", Constants.HEADER_SEC_FETCH_MODE);
        headerMap.put("Sec-Fetch-Site", Constants.HEADER_SEC_FETCH_SITE);
        headerMap.put("X-Client", Constants.HEADER_X_CLIENT);
        headerMap.put("Accept-Language", Constants.HEADER_ACCEPT_LANGUAGE_ENGLISH);
        headerMap.put("Content-Type", Constants.HEADER_CONTENT_TYPE);
        if(cookieString.isPresent())
            headerMap.put("Cookie", cookieString.get());
        return headerMap;
    }
}
