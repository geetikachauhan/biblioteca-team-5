package com.vapasi.biblioteca.controller;


import com.vapasi.biblioteca.response.BookResponse;
import com.vapasi.biblioteca.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    private String userName = "Guest";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("")
    public ResponseEntity<List<BookResponse>> listBooks() {
        logger.info("[Books-List-Action] " + userName );
        return ResponseEntity.ok().body(bookService.listBooks());
    }

    @PutMapping("/{title}/checkout")
    public ResponseEntity<String> checkoutBook(@PathVariable("title") String title) {
        String message = bookService.checkoutBook(title);
        logger.info("[Books-Checkout-Action] " + getUserName() + ": BookTitle:" + title +  " Checkout message: " + message);
        return ResponseEntity.ok().body(message);
    }

    @PutMapping("/{title}/return")
    public ResponseEntity<String> returnBook(@PathVariable("title") String title) {
        String message = bookService.returnBook(title);
        logger.info("[Books-Return-Action] " + getUserName() + ": BookTitle:" + title + " Return message: " + message);
        return ResponseEntity.ok().body(message);
    }

    public String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null) userName = authentication.getName();
        return userName;
    }

}
