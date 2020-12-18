package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.model.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;

    @Test
    void shouldListTheMoviesInAlphabeticalOrder() {
        List<Movie> movieList = movieRepository.findAllByOrderByMovieNameAsc();
        assertEquals(4, movieList.size());
    }

}
