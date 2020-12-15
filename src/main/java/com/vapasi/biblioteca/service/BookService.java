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
        List<Book> allBooks = bookRepository.findAllByOrderByTitleAsc();
        List<BookResponse> allBooksResponse = new ArrayList<>();
        allBooks.stream()
                .filter(book -> isBookAvailable(book))
                .forEach(book -> allBooksResponse.add(new BookResponse(book.getId(), book.getTitle())));
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
        return (book != null && book.isAvailable());
    }
}
