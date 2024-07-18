package com.springboot.moov.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Data
@Entity
public class Landing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String posterUrl;
    private LocalDate releaseDate;

    // Getter, Setter, equals(), hashCode() 생성
}