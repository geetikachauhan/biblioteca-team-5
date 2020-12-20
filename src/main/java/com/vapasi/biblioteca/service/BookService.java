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

    @Autowired
    private BookRegisterService bookRegisterService;

    private static final String MESSAGE_CHECKOUT_SUCCESS = "Thank you! Enjoy the book";
    private static final String MESSAGE_CHECKEDOUTBOOK = "That book has been checked out already.";
    private static final String MESSAGE_CHECKOUT_UNSUCCESSFULL = "That book is not available in Library.";
    private static final String MESSAGE_RETURN_SUCCESS = "Thank you for returning the book";
    private static final String MESSAGE_RETURN_RETURNEDBOOK = "That book has been returned already";
    private static final String MESSAGE_RETURN_UNSUCCESSFULL = "That is not a valid book to return";
    private static final String MESSAGE_RETURN_NOTVALIDUSER = "You are not a valid customer to return this book.";


    public BookService(BookRepository bookRepository, BookRegisterService bookRegisterService) {
        this.bookRepository = bookRepository;
        this.bookRegisterService = bookRegisterService;
    }

    public List<BookResponse> listBooks() {
        List<Book> allBooks = bookRepository.findAllByOrderByTitleAsc();
        List<BookResponse> allBooksResponse = new ArrayList<>();
        allBooks.stream()
                .filter(this::isBookAvailable)
                .forEach(book -> allBooksResponse.add(new BookResponse(book.getTitle(), book.getAuthor(), book.getYearPublished(), book.getIsbn())));
        return allBooksResponse;
    }

    public String checkoutBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book == null)
            return MESSAGE_CHECKOUT_UNSUCCESSFULL;

        if (!book.isAvailable())
            return MESSAGE_CHECKEDOUTBOOK;

        bookRepository.save(new Book(book.getId(), book.getTitle(), book.getAuthor(), book.getYearPublished(), book.getIsbn(), false));
        bookRegisterService.addBookRecord(book.getId());

        return MESSAGE_CHECKOUT_SUCCESS;
    }

    public String returnBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book == null)
            return MESSAGE_RETURN_UNSUCCESSFULL;

        if (book.isAvailable())
            return MESSAGE_RETURN_RETURNEDBOOK;
        if (!bookRegisterService.isValidUserToReturn(book.getId()))
            return MESSAGE_RETURN_NOTVALIDUSER;

        bookRepository.save(new Book(book.getId(), book.getTitle(), book.getAuthor(), book.getYearPublished(), book.getIsbn(), true));
        bookRegisterService.removeBookRecord(book.getId());

        return MESSAGE_RETURN_SUCCESS;
    }


    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public boolean isBookAvailable(Book book) {
        return (book != null && book.isAvailable());
    }
}
