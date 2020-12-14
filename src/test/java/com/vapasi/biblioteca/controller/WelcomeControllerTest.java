package com.vapasi.biblioteca.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(SpringExtension.class)
public class WelcomeControllerTest {

    private final String WELCOME_MESSAGE = "Hello, Welcome to Biblioteca !";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookController bookController;

    @Test
    public void shouldShowWelcomeMessageWhenHitDefaultUrl() throws Exception {
        mockMvc.perform(get("/biblioteca"))
        .andExpect(status().isOk())
        .andExpect(content().string(WELCOME_MESSAGE));
    }
}
