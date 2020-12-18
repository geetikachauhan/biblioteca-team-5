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
    private final String ERROR_MESSAGE = "Page Not Found";
    private final String ERROR_URL = "/hello";

    HttpEntity<String> entity;
    Long id;

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
    void shouldDisplayPageNotFound() {
        ResponseEntity<String> response = this.testRestTemplate.exchange(ERROR_URL, HttpMethod.GET, entity, String.class, id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
