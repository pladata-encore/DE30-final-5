package com.springboot.moov.data.repository;

import com.springboot.moov.data.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Movies, Long> {
    List<Movies> findByGenre(String genre);
}
