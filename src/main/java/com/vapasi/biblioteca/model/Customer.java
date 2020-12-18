package com.vapasi.biblioteca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String name;
    private String libraryNumber;
    private String password;

    public Customer(){

    }

    public Customer(Integer id, String name, String libraryNumber, String password) {
        this.id = id;
        this.name = name;
        this.libraryNumber = libraryNumber;
        this.password = password;
    }

    public Customer(String name, String libraryNumber, String password) {
        this.name = name;
        this.libraryNumber = libraryNumber;
        this.password = password;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(libraryNumber, customer.libraryNumber) &&
                Objects.equals(password, customer.password);
    }

}
