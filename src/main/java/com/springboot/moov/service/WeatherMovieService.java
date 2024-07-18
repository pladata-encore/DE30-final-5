package com.springboot.moov.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.springboot.moov.model.WeatherMovie;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherMovieService {
    private List<WeatherMovie> allMovies;

    @PostConstruct
    public void loadMovies() throws IOException, CsvException {
        allMovies = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/all_movies_data.csv"))) {
            List<String[]> records = reader.readAll();
            for (String[] record : records) {
                WeatherMovie movie = new WeatherMovie();
                movie.setTitle(record[0]);  // title at index 0
                movie.setGenre(record[2]);  // genre at index 2
                movie.setRating(Double.parseDouble(record[4]));  // rating at index 4
                allMovies.add(movie);
            }
        }
    }

    public List<WeatherMovie> recommendMovies(String weatherDescription) {
        String genre;
        if (weatherDescription.contains("천둥번개")) {
            genre = "SF, 스릴러";
        } else if (weatherDescription.contains("이슬비")) {
            genre = "로맨스, 음악, 드라마";
        } else if (weatherDescription.contains("비")) {
            genre = "로맨스, 범죄";
        } else if (weatherDescription.contains("눈")) {
            genre = "가족, 애니메이션";
        } else if (weatherDescription.contains("맑음")) {
            genre = "모험, 코미디, 드라마";
        } else if (weatherDescription.contains("흐림")) {
            genre = "공포, 미스터리";
        } else if (weatherDescription.contains("바람이 많이 부는")) {
            genre = "역사, 서부";
        } else if (weatherDescription.contains("안개")) {
            genre = "다큐멘터리, 모험, 판타지";
        } else if (weatherDescription.contains("먼지")) {
            genre = "서부, 액션";
        } else if (weatherDescription.contains("태풍")) {
            genre = "전쟁, 액션";
        } else {
            genre = "TV영화, 드라마, SF";
        }

        final String finalGenre = genre;
        return allMovies.stream()
                .filter(movie -> movie.getGenre().equalsIgnoreCase(finalGenre))
                .collect(Collectors.toList());
    }
}

