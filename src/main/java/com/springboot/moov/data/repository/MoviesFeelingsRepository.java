package com.springboot.moov.data.repository;

import com.springboot.moov.data.entity.MoviesFeelings;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MoviesFeelingsRepository extends JpaRepository<MoviesFeelings, Long> {
    List<MoviesFeelings> findByGenreOrderByRatingsDesc(String genre);
}
