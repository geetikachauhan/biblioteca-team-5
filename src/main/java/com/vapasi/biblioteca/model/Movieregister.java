package com.vapasi.biblioteca.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Movieregister {

    @Id
    Integer movieId;
    String libraryNumber;

    public Movieregister() {
    }

    public Movieregister(String libraryNumber, Integer movieId) {
        this.libraryNumber = libraryNumber;
        this.movieId = movieId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movieregister that = (Movieregister) o;
        return Objects.equals(movieId, that.movieId) && Objects.equals(libraryNumber, that.libraryNumber);
    }

}
