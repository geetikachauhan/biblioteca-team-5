package com.vapasi.biblioteca.controller;

import com.vapasi.biblioteca.response.MovieResponse;
import com.vapasi.biblioteca.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
@ExtendWith(SpringExtension.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    private final String MESSAGE_CHECKOUT_SUCCESS = "Thank you! Enjoy the movie";
    private final String MESSAGE_CHECKEDOUT_MOVIE = "That movie has been checked out already.";
    private final String MESSAGE_CHECKOUT_UNSUCCESSFULL = "That movie is not available in Library.";

    private final String MOVIES_LIST_URL = "/movies";
    private final String CHECKOUT_SUCCESS_URL = "/movies/How to Train Your Dragon/checkout";
    private final String CHECKOUT_UNSUCCESS_URL = "/movies/Titanic/checkout";
    private final String CHECKOUT_ALREADYCHECKEDOUT_URL = "/movies/The Colour of Magic/checkout";

    @Test
    void shouldListMovies() throws Exception {
        when(movieService.listMovies()).thenReturn(Arrays
                .asList(new MovieResponse("Harry Potter and the Philosopher's Stone", 2001, "Chris Columbus", 8.0F),
                        new MovieResponse("How to Train Your Dragon", 2010, "Chris Sanders", 8.0F)));

        mockMvc.perform(get(MOVIES_LIST_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{" +
                        "    \"movieName\": \"Harry Potter and the Philosopher's Stone\"," +
                        "    \"movieYear\": 2001," +
                        "    \"director\": \"Chris Columbus\"," +
                        "    \"rating\": 8.0" +
                        "  }," +
                        "  {" +
                        "    \"movieName\": \"How to Train Your Dragon\"," +
                        "    \"movieYear\": 2010," +
                        "    \"director\": \"Chris Sanders\"," +
                        "    \"rating\": 8.0" +
                        "  }]"));

    }
    @Test
    void shouldCheckOutExistingMovie() throws Exception {
        when(movieService.checkoutMovie(any())).thenReturn(MESSAGE_CHECKOUT_SUCCESS);

        mockMvc.perform(put(CHECKOUT_SUCCESS_URL).with(user("test").password("test").roles("USER"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(MESSAGE_CHECKOUT_SUCCESS));
    }

    @Test
    void shouldNotCheckOutAlreadyCheckedOutMovie() throws Exception {
        when(movieService.checkoutMovie(any())).thenReturn(MESSAGE_CHECKEDOUT_MOVIE);
        mockMvc.perform(put(CHECKOUT_ALREADYCHECKEDOUT_URL).with(user("test").password("test").roles("USER"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(MESSAGE_CHECKEDOUT_MOVIE));


    }
    @Test
    void shouldNotCheckOutTheMovieNotInLibrary() throws Exception {
        when(movieService.checkoutMovie(any())).thenReturn(MESSAGE_CHECKOUT_UNSUCCESSFULL);
        mockMvc.perform(put(CHECKOUT_UNSUCCESS_URL).with(user("test").password("test").roles("USER"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(MESSAGE_CHECKOUT_UNSUCCESSFULL));
    }

}
