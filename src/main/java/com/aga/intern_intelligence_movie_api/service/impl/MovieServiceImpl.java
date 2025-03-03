package com.aga.intern_intelligence_movie_api.service.impl;

import com.aga.intern_intelligence_movie_api.entity.Movie;
import com.aga.intern_intelligence_movie_api.exception.NotFoundException;
import com.aga.intern_intelligence_movie_api.mapper.MovieMapper;
import com.aga.intern_intelligence_movie_api.model.MovieDto;
import com.aga.intern_intelligence_movie_api.repository.MovieRepository;
import com.aga.intern_intelligence_movie_api.service.MovieService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final Validator validator;


    @Override
    public Page<MovieDto> getAllMovies(Pageable pageable) {
        var moviesPage = movieRepository.findAll(pageable);
        return moviesPage.map(movieMapper::mapToDto);
    }


    @Override
    public void deleteMovie(Integer id) {
        log.info("Deleting movie {}", id);
        movieRepository.deleteById(id);
        log.info("Movie deleted {}", id);
    }


    @Override
    public MovieDto getMovieById(Integer id) {
        log.info("Getting movie by id {}", id);
        var movieEntity = movieRepository.findById(id).orElseThrow(() -> new NotFoundException("MOVIE_NOT_FOUND"));
        log.info("Movie found {}", movieEntity);
        return movieMapper.mapToDto(movieEntity);
    }


    @Override
    public void addMovie(MovieDto movieDto) {
        log.info("Adding movie {}", movieDto);
        Set<ConstraintViolation<MovieDto>> violations = validator.validate(movieDto);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<MovieDto> violation : violations) {
                errorMessage.append(violation.getMessage()).append("; ");
            }
            throw new IllegalArgumentException(errorMessage.toString());
        }

        log.info("Movie added {}", movieDto);

        movieRepository.save(movieMapper.mapToEntity(movieDto));
    }


    @Override
    public void updateMovie(Integer id, MovieDto movieDto) {
        log.info("Updating movie {}", movieDto);
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("MOVIE_NOT_FOUND"));


        if (movieDto.getTitle() != null) {
            movie.setTitle(movieDto.getTitle());
        }
        if (movieDto.getImdb() != null) {
            movie.setImdb(movieDto.getImdb());
        }
        if (movieDto.getReleaseYear() != null) {
            movie.setReleaseYear(movieDto.getReleaseYear());
        }
        if (movieDto.getGenre() != null) {
            movie.setGenre(movieDto.getGenre());
        }

        movieRepository.save(movie);
        log.info("Movie updated {}", movie);
    }


}

