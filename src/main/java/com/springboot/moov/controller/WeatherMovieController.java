package com.springboot.moov.controller;

import com.springboot.moov.data.dto.WeatherMovieDTO;
import com.springboot.moov.service.WeatherMovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherMovieController {

    private final WeatherMovieService weatherMovieService;

    public WeatherMovieController(WeatherMovieService weatherMovieService) {
        this.weatherMovieService = weatherMovieService;
    }

    @GetMapping("/weathermovies")
    public List<WeatherMovieDTO> getMovies(@RequestParam String weather) {
        return weatherMovieService.getRecommendations(weather);
    }
}
