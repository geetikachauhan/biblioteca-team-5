package com.vapasi.biblioteca.controller;


import com.vapasi.biblioteca.response.BookResponse;
import com.vapasi.biblioteca.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${checkout.success}")
    private String checkoutSuccessMessage;

    @Value("${checkout.unsuccessful}")
    private String checkoutUnSuccessMessage;

    @Value("${return.success}")
    private String returnSuccessMessage;

    @Value("${return.unsuccessful}")
    private String returnUnSuccessMessage;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("")
    public ResponseEntity<List<BookResponse>> listBooks() {
        logger.info("[Books-List-Action] Guest : " + new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok().body(bookService.listBooks());
    }


    @PutMapping("/{title}/checkout")
    public ResponseEntity<String> checkoutBook(@PathVariable("title") String title) {
        String checkoutStatus = "Unsuccess";
        String message = checkoutUnSuccessMessage;
        if (bookService.checkoutBook(title)) {
            checkoutStatus = "Success";
            message = checkoutSuccessMessage;
        }
        logger.info("[Books-Checkout-Action] Guest  BookTitle:" + title + "   " + new Timestamp(System.currentTimeMillis()) + "Checkout Status: " + checkoutStatus);
        return ResponseEntity.ok().body(message);
    }

    @PutMapping("/{title}/return")
    public ResponseEntity<String> returnBook(@PathVariable("title") String title) {
        String returnStatus = "Unsuccess";
        String message = returnUnSuccessMessage;
        if (bookService.returnBook(title)) {
            message = returnSuccessMessage;
            returnStatus = "Success";
        }
        logger.info("[Books-Return-Action] Guest  BookTitle:" + title + "   " + new Timestamp(System.currentTimeMillis()) + "Return Status: " + returnStatus);
        return ResponseEntity.ok().body(message);
    }

}
