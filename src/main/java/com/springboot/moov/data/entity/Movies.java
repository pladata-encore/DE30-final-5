package com.springboot.moov.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
