package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
