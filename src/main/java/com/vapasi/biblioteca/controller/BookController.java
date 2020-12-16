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
        logger.info("[Books-List-Action] Guest : " + new Timestamp(System.currentTimeMillis() ));
        return ResponseEntity.ok().body(bookService.listBooks());
    }


    @PutMapping("/{title}/checkout")
    public ResponseEntity<String> checkoutBook(@PathVariable("title") String title) {
        logger.info("[Books-Checkout-Action] Guest : " + new Timestamp(System.currentTimeMillis()) +"  BookTitle:" + title);
        String message=checkoutUnSuccessMessage;
        if(bookService.checkoutBook(title))
            message=checkoutSuccessMessage;
        return ResponseEntity.ok().body(message);
    }

    @PutMapping("/{title}/return")
    public ResponseEntity<String> returnBook(@PathVariable("title") String title) {
        logger.info("[Books-Return-Action] Guest : " + new Timestamp(System.currentTimeMillis()) +"  BookTitle:" + title );
        String message=returnUnSuccessMessage;
        if(bookService.returnBook(title))
            message=returnSuccessMessage;
        return ResponseEntity.ok().body(message);
    }

}
