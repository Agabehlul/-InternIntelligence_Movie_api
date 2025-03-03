package com.aga.intern_intelligence_movie_api.controller;

import com.aga.intern_intelligence_movie_api.model.MovieDto;
import com.aga.intern_intelligence_movie_api.service.impl.MovieServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieServiceImpl movieService;

    @GetMapping("/{id}")
    @Operation(summary = "Get Movie By Id")
    public MovieDto getMovieById(@PathVariable Integer id) {
        return movieService.getMovieById(id);
    }

    @GetMapping
    @Operation(summary = "Get All Movies")
    public ResponseEntity<Page<MovieDto>> getAllMovies(Pageable pageable) {
        Page<MovieDto> movies = movieService.getAllMovies(pageable);
        return ResponseEntity.ok(movies);
    }

    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add Movie")
    public void addMovie(@RequestBody @Valid MovieDto movieDto) {
        movieService.addMovie(movieDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Movie")
    public void updateMovie(@PathVariable Integer id, @RequestBody @Valid MovieDto movieDto) {
        movieService.updateMovie(id, movieDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete movie")
    public void deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
    }

}
