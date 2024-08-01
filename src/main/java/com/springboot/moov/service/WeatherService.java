package com.springboot.moov.service;

import com.springboot.moov.data.response.WeatherResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public interface WeatherService {
    Mono<WeatherResponse> getCurrentWeather(double lat, double lon);
}

