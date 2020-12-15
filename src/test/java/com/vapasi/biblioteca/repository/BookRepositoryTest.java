package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;
    private final String BOOK_TITLE = "The Fellowship of the Ring";
    private final Book AVAILABLE_BOOK = new Book(2, BOOK_TITLE, true);
    private final Book NON_EXISTING_BOOK = new Book(1, "Harry Potter", false);


    @Test
    void shouldListTheBooks() {
        List<Book> bookList = bookRepository.findAll();
        assertEquals(6, bookList.size());
    }

    @Test
    void shouldCheckoutTheExistingBook() {

        Book existingBook = bookRepository.findByTitle(BOOK_TITLE);
        bookRepository.save(new Book(existingBook.getId(), existingBook.getTitle(), false));
        assertFalse(bookRepository.findByTitle(BOOK_TITLE).isAvailable());

    }

    @Test
    void shouldReturnTheExistingBookByTitle() {
        Book existingBook = bookRepository.findByTitle(BOOK_TITLE);
        assertEquals(AVAILABLE_BOOK, existingBook);
    }

    @Test
    void shouldReturnNullForTheNonExistingBook() {
        Book nonExistingBook = bookRepository.findByTitle(NON_EXISTING_BOOK.getTitle());
        assertNull(nonExistingBook);
    }


}
