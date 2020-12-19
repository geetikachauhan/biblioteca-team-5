package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.model.Bookregister;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class BookRegisterRepositoryTest {

    @Autowired
    BookRegisterRepository bookRegisterRepository;

    private final Bookregister BOOK_REGISTER = new Bookregister("123", 1);

    @Test
    void shouldAddRegisterEntry() {
        bookRegisterRepository.save(BOOK_REGISTER);
        assertEquals(BOOK_REGISTER, bookRegisterRepository.findById(BOOK_REGISTER.getBookId()).get());
    }

    @Test
    void shouldRemoveRegisterEntry() {
        bookRegisterRepository.save(BOOK_REGISTER);
        bookRegisterRepository.delete(BOOK_REGISTER);
        assertTrue(bookRegisterRepository.findAll().isEmpty());
    }
}