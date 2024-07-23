package com.springboot.moov.service;

import com.springboot.moov.data.dto.LandingDTO;
import com.springboot.moov.data.entity.Movies;
import com.springboot.moov.data.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LandingService {

    private final MoviesRepository moviesRepository;

    @Autowired
    public LandingService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public List<LandingDTO> getAllLandings() {
        List<Movies> movies = moviesRepository.findAll();
        return movies.stream()
                .map(movie -> new LandingDTO(movie.getTitle(), movie.getPosterUrl(), movie.getReleaseDate()))
                .collect(Collectors.toList());
    }

    public List<LandingDTO> getSortedAndDistinctByReleaseDate() {
        LocalDate currentDate = LocalDate.now();
        List<Movies> movies = moviesRepository.findSortedAndDistinctByReleaseDate(currentDate);
        return movies.stream()
                .map(movie -> new LandingDTO(movie.getTitle(), movie.getPosterUrl(), movie.getReleaseDate()))
                .collect(Collectors.toList());
    }
}
