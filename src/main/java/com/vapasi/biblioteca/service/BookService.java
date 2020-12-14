package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.model.Book;
import com.vapasi.biblioteca.repository.BookRepository;
import com.vapasi.biblioteca.response.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookResponse> listBooks() {
        List<Book> allBooks = bookRepository.findAll();
        List<BookResponse> allBooksResponse = new ArrayList<>();
        allBooks.forEach(book -> allBooksResponse.add(new BookResponse(book.getId(), book.getTitle())));
        return allBooksResponse;
    }
}
