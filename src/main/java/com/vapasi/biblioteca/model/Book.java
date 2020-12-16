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

    private String author;
    private Integer yearPublished;
    private Boolean available;

    public Book() {

    }

    public Book(Integer id, String title, Boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.available = isAvailable;
    }

    public Book(Integer id, String title, String author, Integer yearPublished, Boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.available = available;
    }

    public Integer getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getYearPublished() {
        return yearPublished;
    }

    public Boolean isAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearPublished=" + yearPublished +
                ", available=" + available +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(yearPublished, book.yearPublished) && Objects.equals(available, book.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, yearPublished, available);
    }
}
