package com.springboot.moov.service;

import com.springboot.moov.data.dto.MoviesDetailDto;
import com.springboot.moov.data.entity.Movies;
import com.springboot.moov.data.repository.MovieDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieDetailService {

    private final MovieDetailRepository movieDetailRepository;

    @Autowired
    public MovieDetailService(MovieDetailRepository movieDetailRepository) {
        this.movieDetailRepository = movieDetailRepository;
    }

    public MoviesDetailDto getMovieById(Long id) {
        Movies movie = movieDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        return new MoviesDetailDto(
                movie.getId(),
                movie.getTitle(),
                movie.getOriginallanguage(),
                movie.getGenre(),
                movie.getRuntime(),
                movie.getRating(),
                movie.getReleasedate(),
                movie.getPosterurl(),
                movie.getSynopsis(),
                movie.getTrailer(),
                movie.getCast(),
                movie.getDirector(),
                movie.getSeries()
        );
    }
}
