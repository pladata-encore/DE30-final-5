package com.springboot.moov.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FlaskService {

    @Value("${flask.server.url}")
    private String flaskServerUrl;

    private final RestTemplate restTemplate;

    public FlaskService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getRecommendations(String userInput) {
        String url = flaskServerUrl + "/recommend";

        // Prepare the request body
        String requestBody = "{\"input\": \"" + userInput + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        return response.getBody();
    }
}
