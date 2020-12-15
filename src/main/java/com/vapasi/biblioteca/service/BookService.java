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
    @Autowired
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookResponse> listBooks() {
        List<Book> allBooks = bookRepository.findAll();
        List<BookResponse> allBooksResponse = new ArrayList<>();
        allBooks.forEach(book -> allBooksResponse.add(new BookResponse(book.getId(), book.getTitle())));
        return allBooksResponse;
    }

    public Boolean checkoutBook(String bookTitle) {
        Book book = findBookByTitle(bookTitle);
        if (isBookAvailable(book)) {
            bookRepository.save(new Book(book.getId(), book.getTitle(), false));
            return true;
        }
        return false;

    }

    public Book findBookByTitle(String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }

    public boolean isBookAvailable(Book book) {
        if (book != null && book.isAvailable())
            return true;
        return false;


    }
}
