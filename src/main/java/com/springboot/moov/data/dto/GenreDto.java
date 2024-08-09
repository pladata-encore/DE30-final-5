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
    private String posterUrl;
    private Double rating;
    private LocalDate releaseDate;

    public GenreDto() {}

    public GenreDto(Long id, String title, String genre, String posterUrl, Double rating, LocalDate releaseDate) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.posterUrl = posterUrl;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }
}
