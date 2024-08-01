package com.springboot.moov.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "weather")
public class WeatherEntity {

    @Id
    private Long id; // JPA 엔티티는 반드시 @Id 필드를 가져야 합니다.

    private String main;
    private String description;
    private String icon;

    // Getters and Setters
}
