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

    private final String MESSAGE_CHECKOUT_SUCCESS = "Thank you! Enjoy the book";
    private final String MESSAGE_CHECKOUT_UNSUCCESSFULL="That book is not available in Library.";
    private final String MESSAGE_RETURN_SUCCESS = "Thank you for returning the book";
    private final String MESSAGE_RETURN_UNSUCCESSFULL = "That is not a valid book to return";
    private final String MESSAGE_RETURN_NOT_VALID_USER = "You are not a valid customer to return this book.";

    private final String BOOKS_LIST_URL = "/books";
    private final String CHECKOUT_SUCCESS_URL = "/books/978-1-60309-625-4/checkout";
    private final String CHECKOUT_UNSUCCESS_URL = "/books/945-1-67809-875-9/checkout";

    private final String RETURN_SUCCESS_URL = "/books/978-1-60309-625-4/return";
    private final String RETURN_UNSUCCESS_URL = "/books/945-1-67809-875-9/return";

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
    void shouldListTheBooks() {
        ResponseEntity<String> response = this.restTemplate.exchange(BOOKS_LIST_URL, HttpMethod.GET, entity, String.class, id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldCheckoutBook() {
        restTemplate.exchange(RETURN_SUCCESS_URL, HttpMethod.PUT, entity, String.class, id);

        ResponseEntity<String> response = this.restTemplate.exchange(CHECKOUT_SUCCESS_URL , HttpMethod.PUT, entity, String.class, id);
        assertEquals(MESSAGE_CHECKOUT_SUCCESS, response.getBody());
    }

   @Test
    void shouldNotCheckoutBooksNotInTheLibrary() {
       ResponseEntity<String> response = this.restTemplate.exchange(CHECKOUT_UNSUCCESS_URL, HttpMethod.PUT, entity, String.class, id);
        assertEquals(MESSAGE_CHECKOUT_UNSUCCESSFULL, response.getBody());
    }

    @Test
    void shouldReturnBook() {
       restTemplate.exchange(CHECKOUT_SUCCESS_URL, HttpMethod.PUT, entity, String.class, id);
        ResponseEntity<String> response = this.restTemplate.exchange(RETURN_SUCCESS_URL, HttpMethod.PUT, entity, String.class, id);
        assertEquals(MESSAGE_RETURN_SUCCESS, response.getBody());
    }

   @Test
    void shouldNotReturnBookNotInTheLibrary() {
       ResponseEntity<String> response = this.restTemplate.exchange(RETURN_UNSUCCESS_URL, HttpMethod.PUT, entity, String.class, id);
       assertEquals(MESSAGE_RETURN_UNSUCCESSFULL, response.getBody());
    }

    @Test
    void shouldNotReturnBookAsDifferentCustomer() {
       restTemplate.exchange(CHECKOUT_SUCCESS_URL, HttpMethod.PUT, entity, String.class, id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth("Guest", "guest");
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        entity = new HttpEntity<String>("", httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(RETURN_SUCCESS_URL, HttpMethod.PUT, entity, String.class, id);
        assertEquals(MESSAGE_RETURN_NOT_VALID_USER, response.getBody());
    }

}
