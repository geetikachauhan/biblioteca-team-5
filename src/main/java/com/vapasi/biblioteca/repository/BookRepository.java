package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByOrderByTitleAsc();
    public Book findByIsbn(String isbn);
}
