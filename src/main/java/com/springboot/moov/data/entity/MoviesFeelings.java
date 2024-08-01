package com.springboot.moov.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "movies_feelings")
public class MoviesFeelings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String genre;
    private Double ratings;

    // 기본 생성자
    public MoviesFeelings() {}

    // 매개변수 있는 생성자
    public MoviesFeelings(String title, String description, String genre, Double ratings) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.ratings = ratings;
    }

    // Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getRatings() {
        return ratings;
    }

    public void setRatings(Double ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "MoviesFeelings{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", ratings=" + ratings +
                '}';
    }
}
