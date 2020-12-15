package com.vapasi.biblioteca.controller;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
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

    private final String CHECKOUT_SUCCESS="Thank you! Enjoy the book";
    private final String CHECKOUT_UNSUCCESSFULL="That book is not available.";
    private final String RETURN_SUCCESS="Thank you for returning the book";
    private final String RETURN_UNSUCCESSFULL="That is not a valid book to return";

    @BeforeEach
    public void setUp() {
        id = 2l;
        String requestBody = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        entity = new HttpEntity<String>(requestBody, headers);
    }

    @Test
    void shouldListTheBooks() throws JSONException {
        String response = this.restTemplate.getForObject("/books", String.class);
        JSONAssert.assertEquals("[{id:2} ,{id:3} ,{id:4} ,{id:5} ,{id:6}]", response, false);
    }

    @Test
    void shouldCheckoutBook() {
        String url = "/books/A Game of Thrones/checkout";
        ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.PUT, entity, String.class, id);
        assertEquals(CHECKOUT_SUCCESS, response.getBody());
    }

    @Test
    void shouldNotCheckoutBook() {
        String url = "/books/Harry Potter/checkout";
        ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.PUT, entity, String.class, id);
        assertEquals(CHECKOUT_UNSUCCESSFULL, response.getBody());
    }

    @Test
    void shouldReturnBook() {
        String url = "/books/A Game of Thrones/return";
        ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.PUT, entity, String.class, id);
        assertEquals(RETURN_SUCCESS, response.getBody());
    }

    @Test
    void shouldNotReturnBook() {
        String url = "/books/Harry Potter/return";
        ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.PUT, entity, String.class, id);
        assertEquals(RETURN_UNSUCCESSFULL, response.getBody());
    }
}
