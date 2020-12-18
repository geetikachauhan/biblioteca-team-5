package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findAllByOrderByMovieNameAsc();
}
