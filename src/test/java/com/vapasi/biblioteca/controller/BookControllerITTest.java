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
        String response = this.restTemplate.getForObject("/biblioteca/books/list", String.class);
        JSONAssert.assertEquals("[{id:1} , {id:2} ,{id:3} ,{id:4} ,{id:5} ,{id:6}]", response, false);
    }

    @Test
    void shouldCheckoutBook() {
        String url = "/biblioteca/books/checkout/A Game of Thrones";
        ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.PUT, entity, String.class, id);
        assertEquals("true", response.getBody());
    }

    @Test
    void shouldNotCheckoutBook() {
        String url = "/biblioteca/books/checkout/Harry Potter";
        ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.PUT, entity, String.class, id);
        assertEquals("false", response.getBody());
    }

}
