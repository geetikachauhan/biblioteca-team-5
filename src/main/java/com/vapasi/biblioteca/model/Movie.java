package com.vapasi.biblioteca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String movieName;
    private Integer movieYear;
    private String director;
    private Double rating;

    public Movie() {
    }

    public Movie(Integer id, String movieName, Integer movieYear, String director, Double rating) {
        this.id = id;
        this.movieName = movieName;
        this.movieYear = movieYear;
        this.director = director;
        this.rating = rating;
    }

    public String getMovieName() {
        return movieName;
    }

    public Integer getMovieYear() {
        return movieYear;
    }

    public String getDirector() {
        return director;
    }

    public Double getRating() {
        return rating;
    }

}

