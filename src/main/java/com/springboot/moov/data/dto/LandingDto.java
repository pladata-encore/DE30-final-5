package com.springboot.moov.data.dto;

import java.time.LocalDate;

public class LandingDto {
    private String title;
    private String posterUrl;
    private LocalDate releaseDate;

    public LandingDto(String title, String posterUrl, LocalDate releaseDate) {
        this.title = title;
        this.posterUrl = posterUrl;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
