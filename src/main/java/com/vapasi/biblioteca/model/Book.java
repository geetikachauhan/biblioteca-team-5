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


    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Book() {
    }

    public Book(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}
