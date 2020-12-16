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
    private final String CHECKOUT_SUCCESS = "Thank you! Enjoy the book";
    private final String CHECKOUT_UNSUCCESSFULL = "That book is not available.";
    private final String RETURN_SUCCESS = "Thank you for returning the book";
    private final String RETURN_UNSUCCESSFULL = "That is not a valid book to return";

    private final String BOOKS_LIST_URL = "/books";
    private final String CHECKOUT_SUCCESS_URL = "/books/A Game of Thrones/checkout";
    private final String CHECKOUT_UNSUCCESS_URL = "/books/Harry Potter/checkout";
    private final String RETURN_SUCCESS_URL = "/books/A Game of Thrones/return";
    private final String RETURN_UNSUCCESS_URL = "/books/Harry Potter/return";

    @Test
    void shouldListBooks() throws Exception {
        when(bookService.listBooks()).thenReturn(Arrays
                .asList(new BookResponse("Harry Potter", "J. K. Rowling", 1997),
                        new BookResponse("The Colour of Magic", "Terry Pratchett", 1983)));

        mockMvc.perform(get(BOOKS_LIST_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{" +
                        "    \"title\": \"Harry Potter\"," +
                        "    \"author\": \"J. K. Rowling\"," +
                        "    \"yearPublished\": 1997" +

                        "  }," +
                        "  {" +
                        "    \"title\": \"The Colour of Magic\"," +
                        "    \"author\": \"Terry Pratchett\"," +
                        "    \"yearPublished\": 1983" +
                        "  }]"));

    }


    @Test
    void shouldCheckOutExistingBook() throws Exception {
        when(bookService.checkoutBook(any())).thenReturn(true);
        mockMvc.perform(put(CHECKOUT_SUCCESS_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(CHECKOUT_SUCCESS));
    }

    @Test
    void shouldNotCheckOutUnAvailableBook() throws Exception {
        when(bookService.checkoutBook(any())).thenReturn(false);
        mockMvc.perform(put(CHECKOUT_UNSUCCESS_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(CHECKOUT_UNSUCCESSFULL));


    }

    @Test
    void shouldReturnExistingBook() throws Exception {
        when(bookService.returnBook(any())).thenReturn(true);
        mockMvc.perform(put(RETURN_SUCCESS_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(RETURN_SUCCESS));
    }

    @Test
    void shouldNotReturnNonExistingBook() throws Exception {
        when(bookService.returnBook(any())).thenReturn(false);
        mockMvc.perform(put(RETURN_UNSUCCESS_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(RETURN_UNSUCCESSFULL));


    }

}