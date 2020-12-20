package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.model.Bookregister;
import com.vapasi.biblioteca.model.Movieregister;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class MovieRegisterRepositoryTest {

    @Autowired
    MovieRegisterRepository movieRegisterRepository;

    private final Movieregister MOVIE_REGISTER = new Movieregister("123", 1);

    @Test
    void shouldAddRegisterEntry() {
        movieRegisterRepository.save(MOVIE_REGISTER);
        assertEquals(MOVIE_REGISTER, movieRegisterRepository.findById(MOVIE_REGISTER.getMovieId()).get());
    }

    @Test
    void shouldRemoveRegisterEntry() {
        movieRegisterRepository.save(MOVIE_REGISTER);
        movieRegisterRepository.delete(MOVIE_REGISTER);
        assertTrue(movieRegisterRepository.findAll().isEmpty());
    }
}