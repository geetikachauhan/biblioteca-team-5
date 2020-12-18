package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.model.Movie;
import com.vapasi.biblioteca.repository.MovieRepository;
import com.vapasi.biblioteca.response.MovieResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    MovieRepository movieRepository;
    @InjectMocks
    MovieService movieService;

    @Test
    void shouldReturnListAvailableOfMovies() {
        List<Movie> expectedMovieList = Arrays.asList(new Movie(1, "Harry Potter and the Philosopher's Stone", 2001, "Chris Columbus", 8.0 ));
        List<MovieResponse> expectedMovieResponseList = new ArrayList();
        expectedMovieResponseList.add(new MovieResponse("Harry Potter and the Philosopher's Stone", 2001, "Chris Columbus", 8.0  ));
        when(movieRepository.findAllByOrderByMovieNameAsc()).thenReturn(expectedMovieList);
        List<MovieResponse> actualMovieResponseList = movieService.listMovies();

        assertEquals(expectedMovieResponseList, actualMovieResponseList);
        verify(movieRepository).findAllByOrderByMovieNameAsc();

    }
}
