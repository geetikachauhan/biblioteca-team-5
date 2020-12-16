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


    private final String EXISTING_BOOK_TITLE = "The Fellowship of the Ring";
    private final String BOOK_AUTHOR = "J. R. R. Tolkien";
    private final Integer BOOK_YEAR = 1954;
    private final Book AVAILABLE_BOOK = new Book(1, EXISTING_BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR, "978-1-60309-047-6" , true);
    private final Book UNAVAILABLE_BOOK = new Book(1, EXISTING_BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR, "978-1-60309-047-6" ,false);
    private final Book NON_EXISTING_BOOK = new Book(1, "Harry Potter", "JK Rowling" , 1997, "978-1-60309-025-5" , false);

    @Test
    void shouldReturnListAvailableOfBooks() {
        List<Book> expectedBookList = Arrays.asList(new Book(1, "Harry Potter", "J. K. Rowling", 1997, "978-1-60309-025-5" ,false), new Book(2, "Ponniyin Selvan", "Kalki Krishnamurthy", 1950 ,"978-1-60309-400-5", true));
        List<BookResponse> expectedBookResponseList = new ArrayList();
        expectedBookResponseList.add(new BookResponse("Ponniyin Selvan", "Kalki Krishnamurthy",1950 ,"978-1-60309-400-5"  ));
        when(bookRepository.findAllByOrderByTitleAsc()).thenReturn(expectedBookList);
        List<BookResponse> actualBookResponseList = bookService.listBooks();

        assertEquals(expectedBookResponseList, actualBookResponseList);
        verify(bookRepository).findAllByOrderByTitleAsc();

    }

    @Test
    void shouldReturnTrueForExistingAvailableBooks() {
        when(bookRepository.findByTitle(EXISTING_BOOK_TITLE)).thenReturn(AVAILABLE_BOOK);
        Book book = bookRepository.findByTitle(EXISTING_BOOK_TITLE);
        assertTrue(bookService.isBookAvailable(book));
    }

    @Test
    void shouldReturnFalseForNonExistingBooks() {
        when(bookRepository.findByTitle(EXISTING_BOOK_TITLE)).thenReturn(null);
        Book book = bookRepository.findByTitle(EXISTING_BOOK_TITLE);
        assertFalse(bookService.isBookAvailable(book));
    }

    @Test
    void shouldReturnFalseForExistingUnAvailableBooks() {
        when(bookRepository.findByTitle(EXISTING_BOOK_TITLE)).thenReturn(UNAVAILABLE_BOOK);
        Book book = bookRepository.findByTitle(EXISTING_BOOK_TITLE);
        assertFalse(bookService.isBookAvailable(book));
    }


    @Test
    void shouldCheckoutExistingBook() {
        Book book = AVAILABLE_BOOK;
        Book checkOutBook = new Book(book.getId(), book.getTitle(), book.getAuthor(), book.getYearPublished(), book.getIsbn() ,false);
        when(bookRepository.findByTitle(EXISTING_BOOK_TITLE)).thenReturn(book);
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
        when(bookRepository.findByTitle(EXISTING_BOOK_TITLE)).thenReturn(book);
        assertEquals(book, bookService.findBookByTitle(book.getTitle()));

    }

    @Test
    void shouldReturnNullFOrNonExistingBook() {
        Book book = NON_EXISTING_BOOK;
        when(bookRepository.findByTitle(any())).thenReturn(null);
        assertNull(bookService.findBookByTitle(book.getTitle()));
    }

    @Test
    void shouldNotReturnExistingBookAvailable() {
        Book book = AVAILABLE_BOOK;
        when(bookRepository.findByTitle(EXISTING_BOOK_TITLE)).thenReturn(book);
        assertFalse(bookService.returnBook(book.getTitle()));
    }

    @Test
    void shouldNotReturnNonExistingBook() {
        Book book = NON_EXISTING_BOOK;
        when(bookRepository.findByTitle(any())).thenReturn(null);
        assertFalse(bookService.returnBook(book.getTitle()));
    }

    @Test
    void shouldReturnExistingUnAvailableBook() {
        Book book = UNAVAILABLE_BOOK;
        when(bookRepository.findByTitle(any())).thenReturn(book);
        assertTrue(bookService.returnBook(book.getTitle()));
    }

}
