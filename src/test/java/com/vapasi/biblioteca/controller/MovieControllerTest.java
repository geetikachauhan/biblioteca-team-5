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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
@ExtendWith(SpringExtension.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    private final String MOVIES_LIST_URL = "/movies";

    @Test
    void shouldListMovies() throws Exception {
        when(movieService.listMovies()).thenReturn(Arrays
                .asList(new MovieResponse("Harry Potter and the Philosopher's Stone", 2001, "Chris Columbus", 8.0),
                        new MovieResponse("How to Train Your Dragon", 2010, "Chris Sanders", 8.0)));

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


}
