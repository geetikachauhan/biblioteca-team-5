package com.vapasi.biblioteca.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Timestamp;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @Value("${welcome.message}")
    private String welcomeMessage;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public ResponseEntity<String> getWelcomeMessage() {
        logger.info("[Welcome-Action] Guest : " + new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok().body(welcomeMessage);
    }

}
