package com.springboot.moov.service;

import com.springboot.moov.data.dto.GenreDto;
import com.springboot.moov.data.repository.GenreMovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

@Service
public class GenreMovieService {

    private final GenreMovieRepository genreMovieRepository;

    public GenreMovieService(GenreMovieRepository genreMovieRepository) {
        this.genreMovieRepository = genreMovieRepository;
    }

    public List<GenreDto> getRecommendationsByGenre(String genre) {
        // 장르를 쉼표로 분리하여 리스트로 변환
        List<String> genreList = Arrays.asList(genre.split(",\\s*"));

        // 각 장르에 대해 쿼리 실행 및 결과 결합
        List<GenreDto> movies = genreList.stream()
                .flatMap(g -> genreMovieRepository.findMoviesByGenre(g).stream())
                .distinct() // 중복 제거
                .collect(Collectors.toList());

        return movies;
    }
}
