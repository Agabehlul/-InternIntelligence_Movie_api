package com.aga.intern_intelligence_movie_api.mapper;

import com.aga.intern_intelligence_movie_api.entity.Movie;
import com.aga.intern_intelligence_movie_api.model.MovieDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieDto mapToDto(Movie movie);

    Movie mapToEntity(MovieDto movieDto);
}
