package com.aga.intern_intelligence_movie_api.service;

import com.aga.intern_intelligence_movie_api.model.MovieDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MovieService {
    Page<MovieDto> getAllMovies(Pageable pageable);

    MovieDto getMovieById(Integer id);

    void addMovie(MovieDto movieDto);

    void updateMovie(Integer id, MovieDto movieDto);

    void deleteMovie(Integer id);

}
