package com.springboot.moov.data.dto;

public class MovieDto {
    private Long id;
    private String title;
    private String genre;
    private String poster_url; // 필드명 수정
    private String releaseDate;
    private String description;

    // 기본 생성자
    public MovieDto() {}

    // 매개변수 있는 생성자
    public MovieDto(Long id, String title, String genre, String poster_url, String releaseDate, String description) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.poster_url = poster_url; // 수정된 필드명
        this.releaseDate = releaseDate;
        this.description = description;
    }

    public MovieDto(String title, String description) {
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPoster_url() { // 수정된 Getter
        return poster_url;
    }

    public void setPoster_url(String poster_url) { // 수정된 Setter
        this.poster_url = poster_url;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MovieDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", poster_url='" + poster_url + '\'' + // 수정된 필드명
                ", releaseDate='" + releaseDate + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
