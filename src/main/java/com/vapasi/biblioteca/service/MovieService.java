package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.model.Movie;
import com.vapasi.biblioteca.repository.MovieRegisterRepository;
import com.vapasi.biblioteca.repository.MovieRepository;
import com.vapasi.biblioteca.response.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieRegisterRepository movieRegisterRepository;

    private final String MESSAGE_CHECKOUT_SUCCESS = "Thank you! Enjoy the movie";
    private final String MESSAGE_CHECKEDOUT_MOVIE = "That movie has been checked out already.";
    private final String MESSAGE_CHECKOUT_UNSUCCESSFULL="That movie is not available in Library.";
    private final String MESSAGE_RETURN_SUCCESS = "Thank you for returning the movie";
    private final String MESSAGE_RETURN_RETURNED_MOVIE = "That movie has been returned already";
    private final String MESSAGE_RETURN_UNSUCCESSFULL = "That is not a valid movie to return";

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieResponse> listMovies() {
        List<Movie> allMovie = movieRepository.findAllByOrderByMovieNameAsc();
        List<MovieResponse> allMovieResponse = new ArrayList<>();
        allMovie.forEach(movie -> allMovieResponse.add(new MovieResponse(movie.getMovieName(), movie.getMovieYear(), movie.getDirector(), movie.getRating())));
        return allMovieResponse;
    }



}
