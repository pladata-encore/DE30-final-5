package com.springboot.moov.service;

import com.springboot.moov.data.response.WeatherResponse;
import reactor.core.publisher.Mono;

public interface WeatherService {
    Mono<WeatherResponse> getCurrentWeather(double lat, double lon);
}

