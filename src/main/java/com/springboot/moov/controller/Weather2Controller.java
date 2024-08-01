package com.springboot.moov.controller;

import com.springboot.moov.data.dto.WeatherMovieDTO;
import com.springboot.moov.service.WeatherMovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Weather2Controller {

    private final WeatherMovieService weatherMovieService;

    public Weather2Controller(WeatherMovieService weatherMovieService) {
        this.weatherMovieService = weatherMovieService;
    }

    @GetMapping("/api/rec_weather_2")
    public List<WeatherMovieDTO> getRecommendations(@RequestParam("weather") String weather) {
        // 유효성 검사를 추가할 수 있습니다.
        if (weather == null || weather.isBlank()) {
            throw new IllegalArgumentException("Weather parameter must not be empty");
        }
        return weatherMovieService.getRecommendations(weather);
    }
}
