package com.springboot.moov.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GenreDto {
    private Long id;
    private String title;
    private String genre;
    private String posterurl;
    private float rating;
    private LocalDate releasedate;

    public GenreDto() {}

    public GenreDto(Long id, String title, String genre, String posterUrl, float rating, LocalDate releaseDate) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.posterurl = posterUrl;
        this.rating = rating;
        this.releasedate = releaseDate;
    }
}
