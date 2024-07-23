package com.springboot.moov.data.repository;

import com.springboot.moov.data.entity.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class MoviesRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MoviesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Movies> findAll() {
        String sql = "SELECT * FROM movies";
        return jdbcTemplate.query(sql, new MovieRowMapper());
    }

    public List<Movies> findSortedAndDistinctByReleaseDate(LocalDate currentDate) {
        String sql = "SELECT * FROM movies WHERE releaseDate < ? " +
                "GROUP BY title ORDER BY releaseDate DESC";
        return jdbcTemplate.query(sql, new MovieRowMapper(), currentDate);
    }
}
