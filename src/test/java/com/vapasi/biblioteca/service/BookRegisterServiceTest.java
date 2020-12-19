package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.model.Bookregister;
import com.vapasi.biblioteca.repository.BookRegisterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookRegisterServiceTest {

    @Mock
    BookRegisterRepository bookRegisterRepository;

    @InjectMocks
    BookRegisterService bookRegisterService;

    private final Integer BOOK_ID = 1;
    private final String GUEST_USER = "Guest";
    private final Bookregister BOOK_REGISTER = new Bookregister(GUEST_USER, BOOK_ID);

    @Test
    void shouldReturnGuestAsUserNameWhenNotLoggedIn() {
        assertEquals(GUEST_USER, bookRegisterService.getCurrentUserName());
    }

    @Test
    void shouldAddRecordOnBookRegister() {
        when(bookRegisterRepository.save(BOOK_REGISTER)).thenReturn(BOOK_REGISTER);
        assertEquals(BOOK_REGISTER, bookRegisterService.addBookRecord(BOOK_REGISTER.getBookId()));
    }

    @Test
    void shouldRemoveRecordFromBookRegister() {
        assertEquals(BOOK_REGISTER, bookRegisterService.removeBookRecord(BOOK_ID));
        verify(bookRegisterRepository).delete(BOOK_REGISTER);
    }

}