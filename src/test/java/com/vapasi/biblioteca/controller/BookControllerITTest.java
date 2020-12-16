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
class BookControllerITTest {

    @Autowired
    private TestRestTemplate restTemplate;

    HttpEntity<String> entity;
    Long id;

    private final String CHECKOUT_SUCCESS = "Thank you! Enjoy the book";
    private final String CHECKOUT_UNSUCCESSFULL = "That book is not available.";
    private final String RETURN_SUCCESS = "Thank you for returning the book";
    private final String RETURN_UNSUCCESSFULL = "That is not a valid book to return";

    private final String BOOKS_LIST_URL = "/books";
    private final String CHECKOUT_SUCCESS_URL = "/books/A Game of Thrones/checkout";
    private final String CHECKOUT_UNSUCCESS_URL = "/books/Harry Potter/checkout";
    private final String RETURN_SUCCESS_URL = "/books/A Game of Thrones/return";
    private final String RETURN_UNSUCCESS_URL = "/books/Harry Potter/return";




    @BeforeEach
    public void setUp() {
        id = 2l;
        String requestBody = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        entity = new HttpEntity<String>(requestBody, headers);
    }

    @Test
    void shouldListTheBooks() {
        ResponseEntity<String> response = this.restTemplate.exchange(BOOKS_LIST_URL, HttpMethod.GET, entity, String.class, id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldCheckoutBook() {
        ResponseEntity<String> response = this.restTemplate.exchange(CHECKOUT_SUCCESS_URL , HttpMethod.PUT, entity, String.class, id);
        assertEquals(CHECKOUT_SUCCESS, response.getBody());
    }

    @Test
    void shouldNotCheckoutBook() {
        ResponseEntity<String> response = this.restTemplate.exchange(CHECKOUT_UNSUCCESS_URL, HttpMethod.PUT, entity, String.class, id);
        assertEquals(CHECKOUT_UNSUCCESSFULL, response.getBody());
    }

    @Test
    void shouldReturnBook() {
        ResponseEntity<String> response = this.restTemplate.exchange(RETURN_SUCCESS_URL, HttpMethod.PUT, entity, String.class, id);
        assertEquals(RETURN_SUCCESS, response.getBody());
    }

    @Test
    void shouldNotReturnBook() {
        ResponseEntity<String> response = this.restTemplate.exchange(RETURN_UNSUCCESS_URL, HttpMethod.PUT, entity, String.class, id);
        assertEquals(RETURN_UNSUCCESSFULL, response.getBody());
    }
}
