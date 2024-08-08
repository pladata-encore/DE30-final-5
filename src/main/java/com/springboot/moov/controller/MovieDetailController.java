package com.springboot.moov.controller;

import com.springboot.moov.data.dto.MoviesDetailDto;
import com.springboot.moov.service.MovieDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie_detail")
public class MovieDetailController {

    private final MovieDetailService movieDetailService;

    @Autowired
    public MovieDetailController(MovieDetailService movieDetailService) {
        this.movieDetailService = movieDetailService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoviesDetailDto> getMovieById(@PathVariable Long id) {
        try {
            MoviesDetailDto movieDto = movieDetailService.getMovieById(id);
            return ResponseEntity.ok(movieDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
