package com.springboot.moov.service.impl;

import com.springboot.moov.data.entity.MoviesFeelings;
import com.springboot.moov.data.repository.MoviesFeelingsRepository;
import com.springboot.moov.data.dto.MovieDto;
import com.springboot.moov.service.MoviesFeelingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlaskMoviesFeelingsServiceImpl implements MoviesFeelingsService {

    @Autowired
    private MoviesFeelingsRepository moviesFeelingsRepository;

    private static final String FLASK_API_URL = "http://127.0.0.1:5000/recommend";

    @Override
    public List<MovieDto> getDatabaseRecommendations(String genre) {
        // 데이터베이스에서 장르에 따라 추천을 가져옵니다
        List<MoviesFeelings> movies = moviesFeelingsRepository.findByGenreOrderByRatingsDesc(genre);

        List<MovieDto> movieDtos = new ArrayList<>();
        for (MoviesFeelings movie : movies) {
            movieDtos.add(new MovieDto(movie.getTitle(), movie.getDescription()));
        }
        return movieDtos;
    }

    @Override
    public List<MovieDto> getFlaskRecommendations(String mood) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // JSON 요청 본문 생성
        String requestJson = "{\"input\": \"" + mood + "\"}";

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        // Flask API 호출
        ResponseEntity<MovieRecommendationsResponse> response = restTemplate.exchange(
                FLASK_API_URL,
                HttpMethod.POST,
                entity,
                MovieRecommendationsResponse.class
        );

        return response.getBody().getRecommendations();
    }

    @Override
    public List<MovieDto> getRecommendations(String genre) {
        return null;
    }

    // Flask API 응답을 처리하기 위한 내부 클래스
    class MovieRecommendationsResponse {
        private List<MovieDto> recommendations;

        public List<MovieDto> getRecommendations() {
            return recommendations;
        }

        public void setRecommendations(List<MovieDto> recommendations) {
            this.recommendations = recommendations;
        }
    }
}
