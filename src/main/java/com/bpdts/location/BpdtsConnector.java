package com.bpdts.location;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BpdtsConnector {

    private String baseUrl = "https://bpdts-test-app.herokuapp.com/";

    public String getResponse(String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + url))
                .build();

        String response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        return response;
    }

}




