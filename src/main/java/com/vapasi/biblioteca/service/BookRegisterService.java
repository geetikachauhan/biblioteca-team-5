package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.model.Bookregister;
import com.vapasi.biblioteca.repository.BookRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class BookRegisterService {

    @Autowired
    BookRegisterRepository bookRegisterRepository;

    private static final String GUEST_USER = "Guest";

    public BookRegisterService(BookRegisterRepository bookRegisterRepository) {
        this.bookRegisterRepository = bookRegisterRepository;
    }

    public Bookregister addBookRecord(Integer bookId) {
        return bookRegisterRepository.save(new Bookregister(getCurrentUserName(), bookId));
    }

    public Bookregister removeBookRecord(Integer bookId) {
        Bookregister bookregister = new Bookregister(getCurrentUserName(), bookId);
        bookRegisterRepository.delete(bookregister);
        return bookregister;
    }

    public String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) return authentication.getName();
        return GUEST_USER;
    }
}
