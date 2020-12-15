package com.vapasi.biblioteca.controller;


import com.vapasi.biblioteca.response.BookResponse;
import com.vapasi.biblioteca.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/biblioteca/books")
public class BookController {

    @Autowired

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Value("${checkout.success}")
    private String checkoutSuccessMessage;

    @Value("${checkout.unsuccess}")
    private String checkoutUnSuccessMessage;

    @GetMapping("/list")
    public ResponseEntity<List<BookResponse>> listBooks() {
        return ResponseEntity.ok().body(bookService.listBooks());
    }


    @PutMapping("/checkout/{title}")
    public ResponseEntity<String> checkoutBook(@PathVariable("title") String title) {
        String message=checkoutUnSuccessMessage;
        if(bookService.checkoutBook(title))
            message=checkoutSuccessMessage;
        return ResponseEntity.ok().body(message);
    }

}
