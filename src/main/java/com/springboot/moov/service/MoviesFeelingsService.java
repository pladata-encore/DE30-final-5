package com.springboot.moov.service;

import com.springboot.moov.data.entity.MoviesFeelings;
import java.util.List;

public interface MoviesFeelingsService {
    List<MoviesFeelings> getRecommendations(String genre);
}
