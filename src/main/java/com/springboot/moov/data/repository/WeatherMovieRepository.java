package com.springboot.moov.data.repository;

import com.springboot.moov.data.dto.WeatherMovieDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class WeatherMovieRepository {

    private final JdbcTemplate jdbcTemplate;

    public WeatherMovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<WeatherMovieDTO> findMoviesByGenre(String genre) {
        String sql = "SELECT id, title, genre FROM movies WHERE genre LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{ "%" + genre + "%" }, new MovieRowMapper());
    }

    private static class MovieRowMapper implements RowMapper<WeatherMovieDTO> {
        @Override
        public WeatherMovieDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new WeatherMovieDTO(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("genre")
            );
        }
    }
}
