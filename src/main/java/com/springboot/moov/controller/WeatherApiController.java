package com.springboot.moov.controller;

import com.springboot.moov.data.response.WeatherResponse;
import com.springboot.moov.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherApiController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public WeatherResponse getWeatherByLocation(
            @RequestParam(value = "lat", required = false, defaultValue = "0") double lat,
            @RequestParam(value = "lon", required = false, defaultValue = "0") double lon) {
        return weatherService.getCurrentWeather(lat, lon);
    }
}
