package com.vapasi.biblioteca.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = WelcomeController.class)
@ExtendWith(SpringExtension.class)
class WelcomeControllerTest {

    private final String WELCOME_MESSAGE = "Hello, Welcome to Biblioteca !";
    private final String WELCOME_URL = "/welcome";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldShowWelcomeMessageWhenHitDefaultUrl() throws Exception {
        mockMvc.perform(get(WELCOME_URL))
                .andExpect(status().isOk())
                .andExpect(content().string(WELCOME_MESSAGE));
    }
}
