package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.model.Movieregister;
import com.vapasi.biblioteca.repository.MovieRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MovieRegisterService {
    @Autowired
    MovieRegisterRepository movieRegisterRepository;

    private static final String GUEST_USER = "Guest";

    public MovieRegisterService(MovieRegisterRepository movieRegisterRepository) {
        this.movieRegisterRepository = movieRegisterRepository;
    }

    public Movieregister addMovieRecord(Integer movieId) {
        return movieRegisterRepository.save(new Movieregister(getCurrentUserName(), movieId));
    }

    public String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) return authentication.getName();
        return GUEST_USER;
    }
}
