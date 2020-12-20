package com.vapasi.biblioteca.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class MovieControllerITTest {
    @Autowired
    private TestRestTemplate restTemplate;
    HttpEntity<String> entity;
    Long id;
    private final String MESSAGE_CHECKOUT_SUCCESS = "Thank you! Enjoy the movie";
    private final String MESSAGE_CHECKOUT_UNSUCCESSFULL = "That movie is not available in Library.";

    private final String MOVIES_LIST_URL = "/movies";
    private final String CHECKOUT_SUCCESS_URL = "/movies/How to Train Your Dragon/checkout";
    private final String CHECKOUT_UNSUCCESS_URL = "/movies/Titanic/checkout";

    @BeforeEach
    public void setUp() {
        id = 2l;
        String requestBody = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("test", "test");
        entity = new HttpEntity<String>(requestBody, headers);
    }

    @Test
    void shouldListTheMovies() {
        ResponseEntity<String> response = this.restTemplate.exchange(MOVIES_LIST_URL, HttpMethod.GET, entity, String.class, id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldCheckoutMovie() {
        ResponseEntity<String> response = this.restTemplate.exchange(CHECKOUT_SUCCESS_URL , HttpMethod.PUT, entity, String.class, id);
        assertEquals(MESSAGE_CHECKOUT_SUCCESS, response.getBody());
    }

    @Test
    void shouldNotCheckoutMoviesNotInTheLibrary() {
        ResponseEntity<String> response = this.restTemplate.exchange(CHECKOUT_UNSUCCESS_URL, HttpMethod.PUT, entity, String.class, id);
        assertEquals(MESSAGE_CHECKOUT_UNSUCCESSFULL, response.getBody());
    }
}
