package com.springboot.moov.service.impl;

import com.springboot.moov.service.WeatherService;
import com.springboot.moov.data.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final WebClient webClient;
    private final String apiKey;

    public WeatherServiceImpl(WebClient.Builder webClientBuilder, @Value("${weather.api.key}") String apiKey) {
        this.webClient = webClientBuilder.baseUrl("http://api.openweathermap.org/data/2.5").build();
        this.apiKey = apiKey;
    }

    @Override
    public Mono<WeatherResponse> getCurrentWeather(double lat, double lon) {
        String url = String.format("/weather?lat=%s&lon=%s&units=metric&appid=%s", lat, lon, apiKey);
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(WeatherResponse.class);
    }
}
