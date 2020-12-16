package com.vapasi.biblioteca.response;

import java.util.Objects;

public class BookResponse {

    private String title;
    private String author;
    private Integer yearPublished;
    private String isbn;

    public BookResponse(String title, String author, Integer yearPublished, String isbn) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.isbn = isbn;
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

    public String getIsbn() {
        return isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookResponse that = (BookResponse) o;
        return Objects.equals(title, that.title) && Objects.equals(author, that.author) && Objects.equals(yearPublished, that.yearPublished) && Objects.equals(isbn, that.isbn);
    }

}
