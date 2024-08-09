package com.springboot.moov.service;

import com.springboot.moov.data.dto.GenreDto;
import com.springboot.moov.data.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    private static final Map<String, String> genreMapping = new HashMap<>();

    static {
        genreMapping.put("SF", "science fiction");
        genreMapping.put("가족", "family");
        genreMapping.put("공포", "horror");
        genreMapping.put("다큐멘터리", "documentary");
        genreMapping.put("드라마", "drama");
        genreMapping.put("로맨스", "romance");
        genreMapping.put("모험", "adventure");
        genreMapping.put("미스터리", "mystery");
        genreMapping.put("범죄", "crime");
        genreMapping.put("서부", "western");
        genreMapping.put("스릴러", "thriller");
        genreMapping.put("애니메이션", "animation");
        genreMapping.put("액션", "action");
        genreMapping.put("역사", "history");
        genreMapping.put("음악", "music");
        genreMapping.put("전쟁", "war");
        genreMapping.put("코미디", "comedy");
        genreMapping.put("판타지", "fantasy");
    }

    private String convertGenreToEnglish(String genre) {
        return genreMapping.getOrDefault(genre, genre);
    }

    public List<GenreDto> getMoviesByGenre(String genre) {
        String genreInEnglish = convertGenreToEnglish(genre);
        return genreRepository.findByGenre(genreInEnglish).stream()
                .map(movie -> new GenreDto(
                        movie.getId(),
                        movie.getTitle(),
                        movie.getGenre(),
                        movie.getPosterurl(),
                        movie.getRating(), // float 타입으로 직접 사용
                        movie.getReleasedate()))
                .collect(Collectors.toList());
    }
}
