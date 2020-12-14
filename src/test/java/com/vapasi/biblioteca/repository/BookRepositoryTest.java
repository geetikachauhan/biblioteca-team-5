package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@ExtendWith(SpringExtension.class)
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void shouldListTheBooks(){
        List<Book> bookList = bookRepository.findAll();
        assertEquals(6, bookList.size());
    }
}
