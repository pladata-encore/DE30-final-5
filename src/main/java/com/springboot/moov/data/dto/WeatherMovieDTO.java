package com.springboot.moov.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherMovieDTO {
    private Long id;
    private String title;
    private String genre;
    private String posterUrl;

    public WeatherMovieDTO(Long id, String title, String genre, String posterUrl) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.posterUrl = posterUrl;
    }
}
