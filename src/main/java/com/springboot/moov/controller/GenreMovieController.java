package com.springboot.moov.controller;

import com.springboot.moov.data.dto.GenreDto;
import com.springboot.moov.service.GenreMovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreMovieController {

    private final GenreMovieService genreMovieService;

    public GenreMovieController(GenreMovieService genreMovieService) {
        this.genreMovieService = genreMovieService;
    }

    @GetMapping("/genremovies")
    public List<GenreDto> getMoviesByGenre(@RequestParam String genre) {
        return genreMovieService.getRecommendationsByGenre(genre);
    }
}
