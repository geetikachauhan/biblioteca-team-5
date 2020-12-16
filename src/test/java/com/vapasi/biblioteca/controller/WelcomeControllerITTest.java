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
class WelcomeControllerITTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    private final String WELCOME_MESSAGE = "Hello, Welcome to Biblioteca !";
    private final String WELCOME_URL = "/welcome";

    @Test
    void shouldDisplayWelcomeMessage() {
        String response = testRestTemplate.getForObject(WELCOME_URL , String.class);
        assertEquals( WELCOME_MESSAGE, response);
    }
}
