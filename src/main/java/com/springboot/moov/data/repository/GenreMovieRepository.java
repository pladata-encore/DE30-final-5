package com.springboot.moov.data.repository;

import com.springboot.moov.data.dto.GenreDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class GenreMovieRepository {

    private final JdbcTemplate jdbcTemplate;

    public GenreMovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<GenreDto> findMoviesByGenre(String genre) {
        // 장르가 포함된 영화들을 검색하는 쿼리
        String sql = "SELECT id, title, genre, posterUrl, rating, releaseDate FROM movies WHERE genre LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{ "%" + genre + "%" }, new GenreRowMapper());
    }

    private static class GenreRowMapper implements RowMapper<GenreDto> {
        @Override
        public GenreDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            java.sql.Date releaseDateSql = rs.getDate("releaseDate");
            LocalDate releaseDate = (releaseDateSql != null) ? releaseDateSql.toLocalDate() : null;

            return new GenreDto(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getString("posterUrl"),
                    rs.getFloat("rating"),
                    releaseDate
            );
        }
    }
}
