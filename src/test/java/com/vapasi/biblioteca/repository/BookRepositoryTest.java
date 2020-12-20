package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;
    private final String EXISTING_BOOK_TITLE = "The Fellowship of the Ring";
    private final String NON_EXISTING_BOOK_TITLE = "Harry Potter";
    private final Book AVAILABLE_BOOK = new Book(2, EXISTING_BOOK_TITLE, "J. R. R. Tolkien", 1954, "978-1-634309-047-6", true);


    @Test
    void shouldListTheBooksInAlphabeticalOrder() {
        List<Book> bookList = bookRepository.findAllByOrderByTitleAsc();
        assertEquals(8, bookList.size());
    }

    @Test
    void shouldUpdateTheExistingBook() {
        Book existingBook = bookRepository.findByIsbn(AVAILABLE_BOOK.getIsbn());
        bookRepository.save(new Book(existingBook.getId(), existingBook.getTitle(), existingBook.getAuthor(), existingBook.getYearPublished(), existingBook.getIsbn(), false));
        assertFalse(bookRepository.findByIsbn(AVAILABLE_BOOK.getIsbn()).isAvailable());
    }

    @Test
    void shouldReturnTheBookByIsbn(){
        Book existingBook = bookRepository.findByIsbn(AVAILABLE_BOOK.getIsbn());
        assertEquals(AVAILABLE_BOOK.getIsbn(),existingBook.getIsbn());
    }
}
