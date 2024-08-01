package com.springboot.moov.service;

import com.springboot.moov.data.dto.MovieDto;
import com.springboot.moov.data.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieRecService {

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private WeatherService weatherService;

    public List<MovieDto> recommendMovies(String location) {
        String weather = weatherService.getWeather(location);
        String genre = determineGenreBasedOnWeather(weather);
        return moviesRepository.findMoviesByGenre(genre);
    }

    private String determineGenreBasedOnWeather(String weather) {
        switch (weather) {
            case "Thunderstorm":
                return "SF, Thriller";
            case "Drizzle":
                return "Romance, Music, Drama";
            case "Rain":
                return "Romance, Crime";
            case "Snow":
                return "Family, Animation, Fantasy";
            case "Clear":
                return "Adventure, Comedy, Drama";
            case "Clouds":
                return "Horror, Mystery, Drama";
            case "Windy":
                return "Historical, Western";
            case "Mist":
            case "Fog":
                return "Documentary, Adventure, Fantasy";
            case "Dust":
                return "Western, Action";
            case "Typhoon":
                return "War, Action";
            default:
                return "TV Movie, Drama, SF";
        }
    }
}
