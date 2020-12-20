package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.model.Book;
import com.vapasi.biblioteca.model.Bookregister;
import com.vapasi.biblioteca.repository.BookRegisterRepository;
import com.vapasi.biblioteca.repository.BookRepository;
import com.vapasi.biblioteca.response.BookResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;
    @Mock
    BookRegisterRepository bookRegisterRepository;

    BookService bookService;

    private final String MESSAGE_CHECKOUT_SUCCESS = "Thank you! Enjoy the book";
    private final String MESSAGE_CHECKEDOUTBOOK = "That book has been checked out already.";
    private final String MESSAGE_CHECKOUT_UNSUCCESSFULL = "That book is not available in Library.";
    private final String MESSAGE_RETURN_SUCCESS = "Thank you for returning the book";
    private final String MESSAGE_RETURN_RETURNEDBOOK = "That book has been returned already";
    private final String MESSAGE_RETURN_UNSUCCESSFULL = "That is not a valid book to return";
    private final String MESSAGE_RETURN_NOTVALIDUSER = "You are not a valid customer to return this book.";


    private final String EXISTING_BOOK_TITLE = "The Fellowship of the Ring";
    private final String BOOK_AUTHOR = "J. R. R. Tolkien";
    private final Integer BOOK_YEAR = 1954;
    private final Book AVAILABLE_BOOK = new Book(1, EXISTING_BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR, "978-1-634309-047-6", true);
    private final Book CHECKEDOUT_BOOK = new Book(1, EXISTING_BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR, "978-1-603109-321-6", false);
    private final Book NON_EXISTING_BOOK = new Book(1, "Harry Potter", "JK Rowling", 1997, "978-1-60309-025-5", false);
    private final Bookregister BOOK_REGISTER_WITH_GUEST = new Bookregister("Guest", 1);
    private final Bookregister BOOK_REGISTER_WITH_TEST = new Bookregister("test", 1);

    @BeforeEach
    public void setup() {
        bookService = new BookService(bookRepository, new BookRegisterService(bookRegisterRepository));
    }

    @Test
    void shouldReturnListAvailableOfBooks() {
        List<Book> expectedBookList = Arrays.asList(new Book(1, "Harry Potter", "J. K. Rowling", 1997, "978-1-60309-025-5", false), new Book(2, "Ponniyin Selvan", "Kalki Krishnamurthy", 1950, "978-1-60309-400-5", true));
        List<BookResponse> expectedBookResponseList = new ArrayList();
        expectedBookResponseList.add(new BookResponse("Ponniyin Selvan", "Kalki Krishnamurthy", 1950, "978-1-60309-400-5"));
        when(bookRepository.findAllByOrderByTitleAsc()).thenReturn(expectedBookList);
        List<BookResponse> actualBookResponseList = bookService.listBooks();

        assertEquals(expectedBookResponseList, actualBookResponseList);
        verify(bookRepository).findAllByOrderByTitleAsc();

    }


    @Test
    void shouldCheckoutExistingBookByUsingIsbn() {
        Book book = AVAILABLE_BOOK;
        Book checkOutBook = new Book(book.getId(), book.getTitle(), book.getAuthor(), book.getYearPublished(),
                book.getIsbn(), false);
        when(bookRepository.findByIsbn(AVAILABLE_BOOK.getIsbn())).thenReturn(AVAILABLE_BOOK);
        when(bookRepository.save(any())).thenReturn(checkOutBook);
        assertEquals(MESSAGE_CHECKOUT_SUCCESS, bookService.checkoutBook(book.getIsbn()));


    }

    @Test
    void shouldNotCheckoutBooksNotInLibrary() {
        Book book = NON_EXISTING_BOOK;
        when(bookRepository.findByIsbn(any())).thenReturn(null);
        assertEquals(MESSAGE_CHECKOUT_UNSUCCESSFULL, bookService.checkoutBook(book.getIsbn()));
    }

    @Test
    void shouldNotCheckoutAlreadyCheckedOutBook() {
        Book book = CHECKEDOUT_BOOK;
        when(bookRepository.findByIsbn(book.getIsbn())).thenReturn(book);
        assertEquals(MESSAGE_CHECKEDOUTBOOK, bookService.checkoutBook(book.getIsbn()));
    }


    @Test
    void shouldReturnCheckedOutBook() {
        Book book = CHECKEDOUT_BOOK;
        Book returnedBook = new Book(book.getId(), book.getTitle(), book.getAuthor(), book.getYearPublished(), book.getIsbn(), true);
        when(bookRepository.findByIsbn(CHECKEDOUT_BOOK.getIsbn())).thenReturn(CHECKEDOUT_BOOK);
        when(bookRepository.save(any())).thenReturn(returnedBook);
        when(bookRegisterRepository.findById(CHECKEDOUT_BOOK.getId())).thenReturn(Optional.of(BOOK_REGISTER_WITH_GUEST));
        assertEquals(MESSAGE_RETURN_SUCCESS, bookService.returnBook(book.getIsbn()));
    }

    @Test
    void shouldNotReturnAlreadyReturnedBook() {
        Book book = AVAILABLE_BOOK;
        when(bookRepository.findByIsbn(AVAILABLE_BOOK.getIsbn())).thenReturn(book);
        assertEquals(MESSAGE_RETURN_RETURNEDBOOK, bookService.returnBook(book.getIsbn()));
    }

    @Test
    void shouldNotReturnBooksNotInLibrary() {
        Book book = NON_EXISTING_BOOK;
        when(bookRepository.findByIsbn(NON_EXISTING_BOOK.getIsbn())).thenReturn(null);

        assertEquals(MESSAGE_RETURN_UNSUCCESSFULL, bookService.returnBook(book.getIsbn()));
    }

    @Test
    void shouldReturnTrueForExistingAvailableBooks() {
        assertTrue(bookService.isBookAvailable(AVAILABLE_BOOK));
    }

    @Test
    void shouldReturnNullForBooksNotInLibrary() {
        assertFalse(bookService.isBookAvailable(null));
    }

    @Test
    void shouldReturnFalseForExistingUnAvailableBooks() {
        assertFalse(bookService.isBookAvailable(CHECKEDOUT_BOOK));
    }

    @Test
    void verifyReturningOfBookAgainstTheCustomerWhoCheckedOutTheBook() {
        when(bookRepository.findByIsbn(CHECKEDOUT_BOOK.getIsbn())).thenReturn(CHECKEDOUT_BOOK);
        when(bookRegisterRepository.findById(CHECKEDOUT_BOOK.getId())).thenReturn(Optional.of(BOOK_REGISTER_WITH_GUEST));
        assertEquals(MESSAGE_RETURN_SUCCESS, bookService.returnBook(CHECKEDOUT_BOOK.getIsbn()));
        when(bookRegisterRepository.findById(CHECKEDOUT_BOOK.getId())).thenReturn(Optional.of(BOOK_REGISTER_WITH_TEST));
        assertEquals(MESSAGE_RETURN_NOTVALIDUSER, bookService.returnBook(CHECKEDOUT_BOOK.getIsbn()));
    }

}