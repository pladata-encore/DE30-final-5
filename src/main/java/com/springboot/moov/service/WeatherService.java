package com.springboot.moov.service;

import com.springboot.moov.data.response.WeatherResponse;

public interface WeatherService {
    WeatherResponse getCurrentWeather(double lat, double lon);
}
