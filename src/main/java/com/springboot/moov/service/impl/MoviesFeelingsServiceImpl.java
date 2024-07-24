package com.springboot.moov.service;

import com.springboot.moov.data.entity.MoviesFeelings;
import com.springboot.moov.data.repository.MoviesFeelingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoviesFeelingsServiceImpl implements MoviesFeelingsService {

    @Autowired
    private MoviesFeelingsRepository moviesFeelingsRepository;

    @Override
    public List<MoviesFeelings> getRecommendations(String genre) {
        return moviesFeelingsRepository.findByGenreOrderByRatingsDesc(genre);
    }
}
