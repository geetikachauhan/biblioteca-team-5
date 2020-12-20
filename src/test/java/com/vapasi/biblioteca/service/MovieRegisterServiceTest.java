package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.model.Movieregister;
import com.vapasi.biblioteca.repository.MovieRegisterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieRegisterServiceTest {
    @Mock
    MovieRegisterRepository movieRegisterRepository;

    @InjectMocks
    MovieRegisterService movieRegisterService;

    private final Integer MOVIE_ID = 1;
    private final String GUEST_USER = "Guest";
    private final Movieregister MOVIE_REGISTER_GUEST = new Movieregister("Guest", MOVIE_ID);

    @Test
    void shouldReturnGuestAsUserNameWhenNotLoggedIn() {
        assertEquals(GUEST_USER, movieRegisterService.getCurrentUserName());
    }

    @Test
    void shouldAddRecordOnMovieRegister() {
        when(movieRegisterRepository.save(MOVIE_REGISTER_GUEST)).thenReturn(MOVIE_REGISTER_GUEST);
        assertEquals(MOVIE_REGISTER_GUEST, movieRegisterService.addMovieRecord(MOVIE_REGISTER_GUEST.getMovieId()));
    }
}
