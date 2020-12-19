package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.model.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;

    private final String EXISTING_MOVIE_NAME = "How to Train Your Dragon";
    private final String NON_EXISTING_BOOK_TITLE = "Titanic";
    private final Movie AVAILABLE_MOVIE = new Movie(1, EXISTING_MOVIE_NAME, 2010, "Chris Sanders", 8.2F, true);

    @Test
    void shouldListTheMoviesInAlphabeticalOrder() {
        List<Movie> movieList = movieRepository.findAllByOrderByMovieNameAsc();
        assertEquals(4, movieList.size());
    }

    @Test
    void shouldUpdateTheExistingBook() {
        List<Movie> existingMovieList = movieRepository.findAllByMovieName(EXISTING_MOVIE_NAME);
        Movie existingMovie = existingMovieList.get(0);
        movieRepository.save(new Movie(existingMovie.getId(), existingMovie.getMovieName(), existingMovie.getMovieYear(), existingMovie.getDirector(), existingMovie.getRating(), false));
        assertFalse(movieRepository.findAllByMovieName(EXISTING_MOVIE_NAME).get(0).isAvailable());
    }

    @Test
    void shouldReturnTheExistingMovieByName() {
        List<Movie> existingMovieList = movieRepository.findAllByMovieName(EXISTING_MOVIE_NAME);
        assertEquals(1, existingMovieList.size());
    }

    @Test
    void shouldReturnNullForTheNonExistingMovie() {
        List<Movie> nonExistingMovie = movieRepository.findAllByMovieName(NON_EXISTING_BOOK_TITLE);
        assertEquals(0, nonExistingMovie.size());

    }
}
