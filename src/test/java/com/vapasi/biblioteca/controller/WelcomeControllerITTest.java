package com.vapasi.biblioteca.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.vapasi.biblioteca.controller.WelcomeControllerTest.WELCOME_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class WelcomeControllerITTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void shouldDisplayWelcomeMessage() {
        String response = testRestTemplate.getForObject("/biblioteca/" , String.class);
        assertEquals( WELCOME_MESSAGE, response);
    }
}
