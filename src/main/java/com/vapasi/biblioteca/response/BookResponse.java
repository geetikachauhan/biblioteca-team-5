package com.vapasi.biblioteca.response;

import java.util.Objects;

public class BookResponse {

    private String title;
    private String author;
    private Integer yearPublished;

    public BookResponse(String title, String author, Integer yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookResponse that = (BookResponse) o;
        return Objects.equals(title, that.title) && Objects.equals(author, that.author) && Objects.equals(yearPublished, that.yearPublished);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, yearPublished);
    }
}
