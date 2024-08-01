package com.springboot.moov.data.repository;

import com.springboot.moov.data.dto.MovieDto;
import com.springboot.moov.data.entity.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<MovieDto> findMoviesByGenre(String genre) {
        // 장르를 콤마로 나누어 OR 조건으로 변환
        String[] genres = genre.split(",\\s*");
        String inClause = Stream.of(genres)
                .map(g -> "?")
                .collect(Collectors.joining(", "));

        String sql = "SELECT id, title, genre, poster_url, releaseDate, description " +
                "FROM movies WHERE genre IN (" + inClause + ")";

        return jdbcTemplate.query(sql, new MovieDtoRowMapper(), (Object[]) genres);
    }

    // RowMapper for MovieDto
    private static class MovieDtoRowMapper implements RowMapper<MovieDto> {
        @Override
        public MovieDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            String title = rs.getString("title");
            String genre = rs.getString("genre");
            String posterUrl = rs.getString("poster_url"); // 수정된 필드명
            String releaseDate = rs.getString("releaseDate");
            String description = rs.getString("description");
            return new MovieDto(id, title, genre, posterUrl, releaseDate, description);
        }
    }
}
