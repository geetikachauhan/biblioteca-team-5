package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.model.Book;
import com.vapasi.biblioteca.model.Movie;
import com.vapasi.biblioteca.repository.MovieRepository;
import com.vapasi.biblioteca.response.BookResponse;
import com.vapasi.biblioteca.response.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

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
