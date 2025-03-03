package com.aga.intern_intelligence_movie_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "movies")
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String director;
    private Integer releaseYear;
    private String genre;
    @Column(precision = 3, scale = 2)
    private BigDecimal imdb;

}
