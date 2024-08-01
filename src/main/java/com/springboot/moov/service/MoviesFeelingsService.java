package com.springboot.moov.service;

import com.springboot.moov.data.dto.MovieDto;
import java.util.List;

public interface MoviesFeelingsService {
    List<MovieDto> getDatabaseRecommendations(String genre);  // 데이터베이스에서 추천 가져오기
    List<MovieDto> getFlaskRecommendations(String mood);      // Flask API에서 추천 가져오기

    List<MovieDto> getRecommendations(String genre);
}