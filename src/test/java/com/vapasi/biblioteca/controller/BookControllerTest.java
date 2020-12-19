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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookController.class)
@ExtendWith(SpringExtension.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private final String MESSAGE_CHECKOUT_SUCCESS = "Thank you! Enjoy the book";
    private final String MESSAGE_CHECKEDOUTBOOK = "That book has been checked out already.";
    private final String MESSAGE_CHECKOUT_UNSUCCESSFULL="That book is not available in Library.";
    private final String MESSAGE_RETURN_SUCCESS = "Thank you for returning the book";
    private final String MESSAGE_RETURN_RETURNEDBOOK = "That book has been returned already";
    private final String MESSAGE_RETURN_UNSUCCESSFULL = "That is not a valid book to return";

    private final String BOOKS_LIST_URL = "/books";
    private final String CHECKOUT_SUCCESS_URL = "/books/A Game of Thrones/checkout";
    private final String CHECKOUT_UNSUCCESS_URL = "/books/Harry Potter/checkout";
    private final String CHECKOUT_ALREADYCHECKEDOUT_URL = "/books/The Colour of Magic/checkout";

    private final String RETURN_SUCCESS_URL = "/books/The Colour of Magic/return";
    private final String RETURN_UNSUCCESS_URL = "/books/Harry Potter/return";
    private final String RETURN_ALREADYRETURNED_URL = "/books/A Game of Thrones/return";


    @Test
    void shouldListBooks() throws Exception {
        when(bookService.listBooks()).thenReturn(Arrays
                .asList(new BookResponse("Harry Potter", "J. K. Rowling", 1997 , "978-1-60309-025-5"),
                        new BookResponse("The Colour of Magic", "Terry Pratchett", 1983 ,"978-1-891830-85-3")));

        mockMvc.perform(get(BOOKS_LIST_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{" +
                        "    \"title\": \"Harry Potter\"," +
                        "    \"author\": \"J. K. Rowling\"," +
                        "    \"yearPublished\": 1997," +
                        "    \"isbn\": \"978-1-60309-025-5\"" +

                        "  }," +
                        "  {" +
                        "    \"title\": \"The Colour of Magic\"," +
                        "    \"author\": \"Terry Pratchett\"," +
                        "    \"yearPublished\": 1983," +
                        "    \"isbn\": \"978-1-891830-85-3\"" +
                        "  }]"));

    }


    @Test
    void shouldCheckOutExistingBook() throws Exception {
        when(bookService.checkoutBook(any())).thenReturn(MESSAGE_CHECKOUT_SUCCESS);

        mockMvc.perform(put(CHECKOUT_SUCCESS_URL).with(user("test").password("test").roles("USER"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(MESSAGE_CHECKOUT_SUCCESS));
    }

    @Test
    void shouldNotCheckOutAlreadyCheckedOutBook() throws Exception {
        when(bookService.checkoutBook(any())).thenReturn(MESSAGE_CHECKEDOUTBOOK);
        mockMvc.perform(put(CHECKOUT_ALREADYCHECKEDOUT_URL).with(user("test").password("test").roles("USER"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(MESSAGE_CHECKEDOUTBOOK));


    }
    @Test
    void shouldNotCheckOutTheBookNotInLibrary() throws Exception {
        when(bookService.checkoutBook(any())).thenReturn(MESSAGE_CHECKOUT_UNSUCCESSFULL);
        mockMvc.perform(put(CHECKOUT_UNSUCCESS_URL).with(user("test").password("test").roles("USER"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(MESSAGE_CHECKOUT_UNSUCCESSFULL));


    }

    @Test
    void shouldReturnExistingBook() throws Exception {
        when(bookService.returnBook(any())).thenReturn(MESSAGE_RETURN_SUCCESS);
        mockMvc.perform(put(RETURN_SUCCESS_URL).with(user("test").password("test").roles("USER"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(MESSAGE_RETURN_SUCCESS));
    }

    @Test
    void shouldNotReturnTheAlreadyReturnedBook() throws Exception {
        when(bookService.returnBook(any())).thenReturn(MESSAGE_RETURN_RETURNEDBOOK);
        mockMvc.perform(put(RETURN_ALREADYRETURNED_URL).with(user("test").password("test").roles("USER"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(MESSAGE_RETURN_RETURNEDBOOK));


    }
    @Test
    void shouldNotReturnTheBooksNotInTheLibrary() throws Exception {
        when(bookService.returnBook(any())).thenReturn(MESSAGE_RETURN_UNSUCCESSFULL);
        mockMvc.perform(put(RETURN_UNSUCCESS_URL).with(user("test").password("test").roles("USER"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(MESSAGE_RETURN_UNSUCCESSFULL));


    }


}