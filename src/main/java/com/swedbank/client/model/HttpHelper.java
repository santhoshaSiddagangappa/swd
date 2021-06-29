package com.swedbank.client.model;

import com.swedbank.client.Constants;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.rmi.UnexpectedException;
import java.util.Map;

public class HttpHelper {

    public static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public static HttpResponse<String> get(String uri, Map<String, String> headerMap) throws IOException, InterruptedException {
        HttpRequest.Builder builder = getBuilder(uri, headerMap);
        builder.GET();
        HttpRequest request = builder.build();

        HttpResponse<String> response = checkResponse(request);
        return response;
    }

    public static HttpResponse<String> post(String uri, Map<String, String> headerMap, String payload) throws IOException, InterruptedException {
        HttpRequest.Builder builder = getBuilder(uri, headerMap);
        builder.POST(HttpRequest.BodyPublishers.ofString(payload));
        HttpRequest request = builder.build();

        HttpResponse<String> response = checkResponse(request);
        return response;
    }

    private static HttpResponse<String> checkResponse(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >= 400)
            throw new UnexpectedException("Unexpected response from server");
        return response;
    }

    private static HttpRequest.Builder getBuilder(String uri, Map<String, String> headerMap) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(Constants.BASE_URL + uri));
        if(null!=headerMap)
            setRequestHeaders(builder, headerMap);
        return builder;
    }


    public static void setRequestHeaders(HttpRequest.Builder requestBuilder, Map<String, String> headerMap) {
        for(Map.Entry<String, String> entry: headerMap.entrySet())
            requestBuilder.header(entry.getKey(), entry.getValue());
    }
}
