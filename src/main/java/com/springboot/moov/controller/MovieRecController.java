package com.springboot.moov.controller;

import com.springboot.moov.data.dto.MovieDto;
import com.springboot.moov.service.MovieRecService;
import com.springboot.moov.service.MoviesFeelingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieRecController {

    @Autowired
    private MovieRecService movieRecService;

    @Autowired
    private MoviesFeelingsService moviesFeelingsService;

    // GET 요청을 통한 영화 추천
    @GetMapping("/recommendations")
    public List<MovieDto> recommendMoviesByLocation(@RequestParam String location) {
        return movieRecService.recommendMovies(location);
    }

    // POST 요청을 통한 영화 추천
    @PostMapping("/recommend")
    public List<MovieDto> recommendMoviesByFeelings(@RequestBody String input) {
        return moviesFeelingsService.getRecommendations(input);
    }
}
