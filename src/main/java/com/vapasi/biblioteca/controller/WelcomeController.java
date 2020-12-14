package com.vapasi.biblioteca.controller;

import com.vapasi.biblioteca.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/biblioteca")
public class WelcomeController {

    @Value("${welcome.message}")
    private String welcomeMessage;


    @GetMapping
    public ResponseEntity<String> getWelcomeMessage() {
        return ResponseEntity.ok().body(welcomeMessage);
    }

}
