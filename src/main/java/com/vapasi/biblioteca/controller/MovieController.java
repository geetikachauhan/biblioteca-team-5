package com.vapasi.biblioteca.controller;

import com.vapasi.biblioteca.response.MovieResponse;
import com.vapasi.biblioteca.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    private String userName = "Guest";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("")
    public ResponseEntity<List<MovieResponse>> listMovies() {
        logger.info("[Movies-List-Action] Guest : " + new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok().body(movieService.listMovies());
    }

    @PutMapping("/{movieName}/checkout")
    public ResponseEntity<String> checkoutMovie(@PathVariable("movieName") String movieName) {
        String message = movieService.checkoutMovie(movieName);
        logger.info("[Movies-Checkout-Action] " + getUserName() + ": MovieName:" + movieName + " Checkout message: " + message);
        return ResponseEntity.ok().body(message);
    }

    public String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) userName = authentication.getName();
        return userName;
    }
}
