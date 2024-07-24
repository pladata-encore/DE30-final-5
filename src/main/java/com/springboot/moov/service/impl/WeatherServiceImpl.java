package com.springboot.moov.service.impl;

import com.springboot.moov.data.response.WeatherResponse;
import com.springboot.moov.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final String apiKey = "c010bb541820e963df4f7fd4807a2391"; // 실제 API 키로 대체
    private final String apiUrl = "https://api.openweathermap.org/data/2.5/weather";

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public WeatherResponse getCurrentWeather(double lat, double lon) {
        String url = String.format("%s?lat=%f&lon=%f&appid=%s&units=metric", apiUrl, lat, lon, apiKey);
        return restTemplate.getForObject(url, WeatherResponse.class);
    }
}
