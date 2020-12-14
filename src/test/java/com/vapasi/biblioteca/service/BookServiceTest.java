package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.model.Book;
import com.vapasi.biblioteca.repository.BookRepository;
import com.vapasi.biblioteca.response.BookResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;
    @InjectMocks
    BookService bookService;


    @Test
    void shouldReturnListOfMenu(){
        List<Book> expectedBookList = Arrays.asList(new Book(1, "Harry Potter"), new Book(2, "Ponniyin Selvan"));
        List<BookResponse> expectedBookResponseList = new ArrayList();
        expectedBookList.forEach(book -> expectedBookResponseList.add(new BookResponse(book.getId(), book.getTitle())));
        when(bookRepository.findAll()).thenReturn(expectedBookList);

        List<BookResponse> actualBookResponseList = bookService.listBooks();

        assertEquals(expectedBookResponseList , actualBookResponseList);
        verify(bookRepository).findAll();

    }
}

