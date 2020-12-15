package com.vapasi.biblioteca.controller;


import com.vapasi.biblioteca.response.BookResponse;
import com.vapasi.biblioteca.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/list")
    public ResponseEntity<List<BookResponse>> listBooks() {
        return ResponseEntity.ok().body(bookService.listBooks());
    }


    @PutMapping("/checkout/{title}")
    public ResponseEntity<Boolean> checkoutBook(@PathVariable("title") String title) {
        return ResponseEntity.ok().body(bookService.checkoutBook(title));
    }

}
