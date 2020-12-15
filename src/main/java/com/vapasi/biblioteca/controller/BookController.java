package com.vapasi.biblioteca.controller;

import com.vapasi.biblioteca.response.BookResponse;
import com.vapasi.biblioteca.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bibliotecadb/books")
public class BookController {

    @Autowired
    private final BookService bookService;
    public BookController(BookService bookService) { this.bookService = bookService; }

    @GetMapping("/list")
    public ResponseEntity<List<BookResponse>> listBooks(){
        return ResponseEntity.ok().body(bookService.listBooks());
    }

}
