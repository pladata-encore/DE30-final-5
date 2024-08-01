package com.springboot.moov.data.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Movies {
    private Long id;
    private String title;
    private String original_language;
    private String genre;
    private int runtime;
    private float rating;
    private LocalDate releaseDate;
    private String poster_url;
    private String plot;
    private String trailer_url;
    private String actors;
    private String director;
    private String series;
}
