package com.vapasi.biblioteca.exceptions;

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
class ExceptionResolverTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    private final String BAD_REQUEST_ERROR_MESSAGE = "Page Not Found";
    private final String BAD_REQUEST_ERROR_URL = "/hello";
    private final String UNAUTHORIZED_ERROR_MESSAGE = "Page Not Found";
    private final String UNAUTHORIZED_ERROR_URL = "/customer";

    HttpEntity<String> entity;
    Long id;
    HttpHeaders headers = new HttpHeaders();
    String requestBody = "";

    @BeforeEach
    public void setUp() {
        id = 2l;
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void shouldDisplayPageNotFound() {
        headers.setBasicAuth("test", "test");
        entity = new HttpEntity<String>(requestBody, headers);
        ResponseEntity<String> response = this.testRestTemplate.exchange(BAD_REQUEST_ERROR_URL, HttpMethod.GET, entity, String.class, id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(BAD_REQUEST_ERROR_MESSAGE, response.getBody());

    }

    @Test
    void shouldDisplayUnauthorizedException() {
        headers.setBasicAuth("test1", "test1");
        entity = new HttpEntity<String>(requestBody, headers);
        ResponseEntity<String> response = this.testRestTemplate.exchange(UNAUTHORIZED_ERROR_URL, HttpMethod.GET, entity, String.class, id);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}
