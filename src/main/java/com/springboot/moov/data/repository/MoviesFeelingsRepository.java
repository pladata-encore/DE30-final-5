package com.springboot.moov.data.repository;

import com.springboot.moov.data.entity.MoviesFeelings;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface MoviesFeelingsRepository extends CrudRepository<MoviesFeelings, Long> {
    List<MoviesFeelings> findByGenreOrderByRatingsDesc(String genre);
}
