package com.springboot.moov.controller;

import com.springboot.moov.data.dto.MovieDto;
import com.springboot.moov.service.MoviesFeelingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class MoviesFeelingsController {

    @Autowired
    private MoviesFeelingsService moviesFeelingsService;

    @GetMapping
    public List<MovieDto> getRecommendations(@RequestParam(name = "genre", defaultValue = "Drama") String genre) {
        return moviesFeelingsService.getRecommendations(genre);
    }
}
