package com.springboot.moov.data.repository;

import com.springboot.moov.data.entity.Movies;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MovieRowMapper implements RowMapper<Movies> {
    @Override
    public Movies mapRow(ResultSet rs, int rowNum) throws SQLException {
        Movies movies = new Movies();
        movies.setId(rs.getLong("id"));
        movies.setTitle(rs.getString("title"));
        movies.setOriginallanguage((rs.getString("originalLanguage")));
        movies.setGenre(rs.getString("genre"));
        movies.setRuntime(rs.getInt("runtime"));
        movies.setRating(rs.getFloat("rating"));

        // Null 체크 후 LocalDate 변환
        java.sql.Date sqlDate = rs.getDate("releaseDate");
        if (sqlDate != null) {
            movies.setReleasedate(sqlDate.toLocalDate());
        } else {
            movies.setReleasedate(null); // 또는 적절한 기본값 설정
        }

        movies.setPosterurl((rs.getString("posterUrl")));
        movies.setSynopsis(rs.getString("synopsis"));
        movies.setTrailer(rs.getString("trailer"));
        movies.setCast(rs.getString("cast"));
        movies.setDirector(rs.getString("director"));
        movies.setSeries(rs.getString("series"));

        return movies;
    }
}
