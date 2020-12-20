package com.vapasi.biblioteca.model;

import javax.persistence.Column;
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

    @Column(
            columnDefinition = "NUMERIC(19,0)"
    )
    private Float rating;
    private Boolean available;

    public Movie() {
    }

    public Movie(Integer id, String movieName, Integer movieYear, String director, Float rating, Boolean available) {
        this.id = id;
        this.movieName = movieName;
        this.movieYear = movieYear;
        this.director = director;
        this.rating = rating;
        this.available = available;
    }

    public Integer getId() {
        return id;
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

    public Float getRating() {
        return rating;
    }

    public Boolean isAvailable() {
        return available;
    }
}

