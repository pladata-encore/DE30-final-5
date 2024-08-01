package com.springboot.moov.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@RestController
public class WeatherApi2Controller {

    private final WebClient webClient;
    private final String apiKey;

    public WeatherApi2Controller(WebClient.Builder webClientBuilder, @Value("${weather.api.key}") String apiKey) {
        this.webClient = webClientBuilder.baseUrl("http://api.openweathermap.org/data/2.5").build();
        this.apiKey = apiKey;
    }

    @GetMapping("/external/weather")
    public Map<String, Object> getWeather(@RequestParam("lat") double lat, @RequestParam("lon") double lon) {
        String url = String.format("/weather?lat=%s&lon=%s&units=metric&appid=%s", lat, lon, apiKey);
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
}
