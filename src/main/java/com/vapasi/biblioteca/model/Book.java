package com.vapasi.biblioteca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String title;

    private Boolean available;

    public Book() {

    }

    public Book(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Book(Integer id, String title, Boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.available = isAvailable;
    }

    public Book(String title, Boolean isAvailable) {
        this.title = title;
        this.available = isAvailable;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public Boolean isAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", available=" + available +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return available == book.available &&
                Objects.equals(id, book.id) &&
                Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, available);

    }
}
