package com.vapasi.biblioteca.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Bookregister {

    String libraryNumber;

    @Id
    Integer bookId;

    public Bookregister() {
    }

    public Bookregister(String libraryNumber, Integer bookId) {
        this.libraryNumber = libraryNumber;
        this.bookId = bookId;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public Integer getBookId() {
        return bookId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;
        Bookregister thatBookregister = (Bookregister) that;
        return Objects.equals(libraryNumber, thatBookregister.libraryNumber) && Objects.equals(bookId, thatBookregister.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libraryNumber, bookId);
    }
}
