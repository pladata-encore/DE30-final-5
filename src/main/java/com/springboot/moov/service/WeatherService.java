package com.springboot.moov.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@Service
public class WeatherService {
    @Value("${c010bb541820e963df4f7fd4807a2391}")
    private String apiKey;

    public String getWeather(double latitude, double longitude) {
        String url = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s&units=metric", latitude, longitude, apiKey);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        JSONObject json = new JSONObject(response);
        return json.getJSONArray("weather").getJSONObject(0).getString("description");
    }
}