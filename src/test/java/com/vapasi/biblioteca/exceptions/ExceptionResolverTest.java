package com.vapasi.biblioteca.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class ExceptionResolverTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    private final String ERROR_MESSAGE = "Page Not Found";
    private final String ERROR_URL = "/hello";

    @Test
    void shouldDisplayWelcomeMessage() {
        String response = testRestTemplate.getForObject(ERROR_URL , String.class);
        System.out.println(response);
        assertEquals( ERROR_MESSAGE, response);
    }
}
