package com.springboot.moov.data.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Movies {
    private Long id;
    private String title;
    private String originallanguage;
    private String genre;
    private int runtime;
    private float rating;
    private LocalDate releasedate;
    private String posterurl;
    private String synopsis;
    private String trailer;
    private String cast;
    private String director;
    private String series;
}
