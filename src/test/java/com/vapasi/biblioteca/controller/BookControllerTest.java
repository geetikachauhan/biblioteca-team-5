package com.vapasi.biblioteca.controller;


import com.vapasi.biblioteca.response.BookResponse;
import com.vapasi.biblioteca.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ExtendWith(SpringExtension.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;
    private final String BOOK_TITLE = "The Fellowship of the Ring";

    @Test
    void shouldListBooks() throws Exception {
        when(bookService.listBooks()).thenReturn(Arrays.asList(new BookResponse(1, "Harry Potter"), new BookResponse(2, "The Colour of Magic")));
        mockMvc.perform(get("/books")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{" +
                        "    \"id\": 1," +
                        "    \"title\": \"Harry Potter\"" +
                        "  }," +
                        "  {" +
                        "    \"id\": 2," +
                        "    \"title\": \"The Colour of Magic\"" +
                        "  }]"));

    }


    @Test
    void shouldCheckOutExistingBook() throws Exception {
        when(bookService.checkoutBook(any())).thenReturn(true);
        mockMvc.perform(put("/books/Harry Potter/checkout")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Thank you! Enjoy the book"));
    }

    @Test
    void shouldNotCheckOutUnAvailableBook() throws Exception {
        when(bookService.checkoutBook(any())).thenReturn(false);
        mockMvc.perform(put("/books/Harry Potter/checkout")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("That book is not available."));


    }

}