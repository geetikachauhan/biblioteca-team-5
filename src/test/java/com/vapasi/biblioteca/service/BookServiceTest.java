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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;
    @InjectMocks
    BookService bookService;


    private final String BOOK_TITLE = "The Fellowship of the Ring";
    private final Book AVAILABLE_BOOK = new Book(1, BOOK_TITLE, true);
    private final Book UNAVAILABLE_BOOK = new Book(1, BOOK_TITLE, false);
    private final Book NON_EXISTING_BOOK = new Book(1, "Harry Potter", false);

    @Test
    void shouldReturnListOfBooks() {
        List<Book> expectedBookList = Arrays.asList(new Book(1, "Harry Potter"), new Book(2, "Ponniyin Selvan"));
        List<BookResponse> expectedBookResponseList = new ArrayList();
        expectedBookList.forEach(book -> expectedBookResponseList.add(new BookResponse(book.getId(), book.getTitle())));
        when(bookRepository.findAll()).thenReturn(expectedBookList);

        List<BookResponse> actualBookResponseList = bookService.listBooks();

        assertEquals(expectedBookResponseList, actualBookResponseList);
        verify(bookRepository).findAll();

    }

    @Test
    void shouldReturnTrueForExistingBooksAvailableBooks() {
        when(bookRepository.findByTitle(BOOK_TITLE)).thenReturn(AVAILABLE_BOOK);
        Book book = bookRepository.findByTitle(BOOK_TITLE);
        System.out.println(book);
        assertTrue(bookService.isBookAvailable(book));
    }

    @Test
    void shouldReturnFalseForNonExistingBooks() {
        when(bookRepository.findByTitle(BOOK_TITLE)).thenReturn(null);
        Book book = bookRepository.findByTitle(BOOK_TITLE);
        assertFalse(bookService.isBookAvailable(book));
    }

    @Test
    void shouldReturnFalseForExistingUnAvailableBooks() {
        when(bookRepository.findByTitle(BOOK_TITLE)).thenReturn(UNAVAILABLE_BOOK);
        Book book = bookRepository.findByTitle(BOOK_TITLE);
        System.out.println(book);
        assertFalse(bookService.isBookAvailable(book));
    }


    @Test
    void shouldCheckoutExistingBook() {
        Book book = AVAILABLE_BOOK;
        Book checkOutBook = new Book(book.getId(), book.getTitle(), false);
        when(bookRepository.findByTitle(BOOK_TITLE)).thenReturn(book);
        when(bookRepository.save(any())).thenReturn(checkOutBook);
        assertTrue(bookService.checkoutBook(book.getTitle()));

    }

    @Test
    void shouldNotCheckoutNonExistingBook() {
        Book book = NON_EXISTING_BOOK;
        when(bookRepository.findByTitle(any())).thenReturn(null);
        assertFalse(bookService.checkoutBook(book.getTitle()));
    }

    @Test
    void shouldNotCheckoutExistingUnAvailableBook() {
        Book book = UNAVAILABLE_BOOK;
        when(bookRepository.findByTitle(any())).thenReturn(book);
        assertFalse(bookService.checkoutBook(book.getTitle()));
    }

    @Test
    void shouldFindTheExistingBookByTitle() {
        Book book = AVAILABLE_BOOK;
        when(bookRepository.findByTitle(BOOK_TITLE)).thenReturn(book);
        assertEquals(book, bookService.findBookByTitle(book.getTitle()));

    }

    @Test
    void shouldReturnNullFOrNonExistingBook() {
        Book book = NON_EXISTING_BOOK;
        when(bookRepository.findByTitle(any())).thenReturn(null);
        assertNull(bookService.findBookByTitle(book.getTitle()));
    }


}
