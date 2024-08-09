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
    private float rating; // float 타입으로 유지
    private LocalDate releaseDate;

    public GenreDto() {}

    // 생성자에서 rating을 float으로 처리
    public GenreDto(Long id, String title, String genre, String posterUrl, float rating, LocalDate releaseDate) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.posterUrl = posterUrl;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }
}