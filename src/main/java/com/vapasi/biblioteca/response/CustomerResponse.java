package com.vapasi.biblioteca.response;

import java.util.Objects;

public class CustomerResponse {

    private String name;
    private String libraryNumber;
    private String password;

    public CustomerResponse(){

    }

    public CustomerResponse(String name, String libraryNumber, String password) {
        this.name = name;
        this.libraryNumber = libraryNumber;
        this.password = password;
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
        CustomerResponse that = (CustomerResponse) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(libraryNumber, that.libraryNumber) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, libraryNumber, password);
    }


}
