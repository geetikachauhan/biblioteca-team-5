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
    private final String EXISTING_BOOK_TITLE = "The Fellowship of the Ring";
    private final String NON_EXISTING_BOOK_TITLE = "Harry Potter";
    private final Book AVAILABLE_BOOK = new Book(2, EXISTING_BOOK_TITLE, "J. R. R. Tolkien", 1954, true);



    @Test
    void shouldListTheBooksInAlphabeticalOrder() {
        List<Book> bookList = bookRepository.findAllByOrderByTitleAsc();
        assertEquals(6, bookList.size());
    }

    @Test
    void shouldUpdateTheExistingBook() {

        Book existingBook = bookRepository.findByTitle(EXISTING_BOOK_TITLE);
        bookRepository.save(new Book(existingBook.getId(), existingBook.getTitle(), existingBook.getAuthor(),existingBook.getYearPublished(), false));
        assertFalse(bookRepository.findByTitle(EXISTING_BOOK_TITLE).isAvailable());

    }

    @Test
    void shouldReturnTheExistingBookByTitle() {
        Book existingBook = bookRepository.findByTitle(EXISTING_BOOK_TITLE);
        assertEquals(AVAILABLE_BOOK, existingBook);
    }

    @Test
    void shouldReturnNullForTheNonExistingBook() {
        Book nonExistingBook = bookRepository.findByTitle(NON_EXISTING_BOOK_TITLE);
        assertNull(nonExistingBook);
    }


}
