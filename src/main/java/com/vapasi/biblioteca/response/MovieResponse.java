package com.vapasi.biblioteca.response;

import java.util.Objects;

public class MovieResponse {
    private String movieName;
    private Integer movieYear;
    private String director;
    private Double rating;

    public MovieResponse(String movieName, Integer movieYear, String director, Double rating) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieResponse that = (MovieResponse) o;
        return Objects.equals(movieName, that.movieName) && Objects.equals(movieYear, that.movieYear) && Objects.equals(director, that.director) && Objects.equals(rating, that.rating);
    }
}
