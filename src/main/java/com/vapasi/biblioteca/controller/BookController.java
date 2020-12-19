package com.vapasi.biblioteca.controller;


import com.vapasi.biblioteca.response.BookResponse;
import com.vapasi.biblioteca.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("")
    public ResponseEntity<List<BookResponse>> listBooks() {
        logger.info("[Books-List-Action] Guest : " + new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok().body(bookService.listBooks());
    }


    @PutMapping("/{title}/checkout")
    public ResponseEntity<String> checkoutBook(@PathVariable("title") String title) {
        String message = bookService.checkoutBook(title);
        logger.info("[Books-Checkout-Action] Guest  BookTitle:" + title + "   " + new Timestamp(System.currentTimeMillis()) + "   Checkout message: " + message);
        return ResponseEntity.ok().body(message);
    }

    @PutMapping("/{title}/return")
    public ResponseEntity<String> returnBook(@PathVariable("title") String title) {
        String message = bookService.returnBook(title);
        logger.info("[Books-Return-Action] Guest  BookTitle:" + title + "   " + new Timestamp(System.currentTimeMillis()) + "    Return message: " + message);
        return ResponseEntity.ok().body(message);
    }

}
