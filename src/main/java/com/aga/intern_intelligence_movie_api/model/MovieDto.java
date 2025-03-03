package com.aga.intern_intelligence_movie_api.model;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MovieDto {

    @NotBlank(message = "Title cannot be empty.")
    @Size(max = 50, message = "Title can be at most 255 characters long.")
    @Pattern(regexp = "^[a-zA-ZğüşöçİĞÜŞÖÇ]+(?: [a-zA-ZğüşöçİĞÜŞÖÇ]+)*$",
            message = "Title must contain only letters, and spaces should only be between words.")
    private String title;


    @NotBlank(message = "Director cannot be empty.")
    @Size(max = 50, message = "Director name can be at most 100 characters long.")
    @Pattern(regexp = "^(?!.*\\d)[A-Za-z]+(?: [A-Za-z]+)*$",
            message = "Director name can only contain English letters (A-Z, a-z) and spaces, without numbers or special characters.")
    private String director;

    @NotNull(message = "Release year cannot be empty.")
    @Min(value = 1000, message = "Release year must be a 4-digit number.")
    @Max(value = 9999, message = "Release year must be a 4-digit number.")
    private Integer releaseYear;


    @NotBlank(message = "Genre cannot be empty.")
    @Size(max = 50, message = "Genre can be at most 50 characters long.")
    @Pattern(regexp = "^[a-zA-ZğüşöçİĞÜŞÖÇ]+(?: [a-zA-ZğüşöçİĞÜŞÖÇ]+)*$",
            message = "Genre must contain only letters, and spaces should only be between words.")
    private String genre;

    @NotNull(message = "IMDB rating cannot be empty.")
    @DecimalMin(value = "0.0", message = "IMDB rating must be at least 0.")
    @DecimalMax(value = "10.0", message = "IMDB rating must be at most 10.")
    private BigDecimal imdb;

}
