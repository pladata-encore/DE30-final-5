package com.springboot.moov.data.dto;

import java.time.LocalDate;

public class MoviesDetailDto {
    private Long id;
    private String title;
    private String originalLanguage;
    private String genre;
    private int runtime;
    private float rating;
    private LocalDate releaseDate;
    private String posterUrl;
    private String synopsis;
    private String trailer;
    private String cast;
    private String director;
    private String series;

    // Constructor
    public MoviesDetailDto(Long id, String title, String originalLanguage, String genre, int runtime, float rating, LocalDate releaseDate, String posterUrl, String synopsis, String trailer, String cast, String director, String series) {
        this.id = id;
        this.title = title;
        this.originalLanguage = originalLanguage;
        this.genre = genre;
        this.runtime = runtime;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.synopsis = synopsis;
        this.trailer = trailer;
        this.cast = cast;
        this.director = director;
        this.series = series;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getOriginalLanguage() { return originalLanguage; }
    public void setOriginalLanguage(String originalLanguage) { this.originalLanguage = originalLanguage; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public int getRuntime() { return runtime; }
    public void setRuntime(int runtime) { this.runtime = runtime; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    public String getPosterUrl() { return posterUrl; }
    public void setPosterUrl(String posterUrl) { this.posterUrl = posterUrl; }

    public String getSynopsis() { return synopsis; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }

    public String getTrailer() { return trailer; }
    public void setTrailer(String trailer) { this.trailer = trailer; }

    public String getCast() { return cast; }
    public void setCast(String cast) { this.cast = cast; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public String getSeries() { return series; }
    public void setSeries(String series) { this.series = series; }
}
