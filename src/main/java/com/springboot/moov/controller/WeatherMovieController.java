package com.springboot.moov.controller;

import com.springboot.moov.model.WeatherMovie;
import com.springboot.moov.service.WeatherMovieService;
import com.springboot.moov.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.json.JSONObject;

import java.util.List;

@Controller
public class WeatherMovieController {
    @Autowired
    private WeatherMovieService weatherMovieService;

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/home")
    public String home() {
        return "weather-movie-recommendation";
    }

    @GetMapping("/weather")
    @ResponseBody
    public JSONObject getWeather(@RequestParam double latitude, @RequestParam double longitude) {
        return weatherService.getWeather(latitude, longitude);
    }

    @GetMapping("/weather-recommendation")
    public String recommendMovies(@RequestParam double latitude, @RequestParam double longitude, Model model) {
        JSONObject weatherJson = weatherService.getWeather(latitude, longitude);
        String weatherDescription = weatherJson.getString("weatherDescription");
        List<WeatherMovie> recommendedMovies = weatherMovieService.recommendMovies(weatherDescription);
        model.addAttribute("movies", recommendedMovies);
        return "weather-recommendation";
    }
}
