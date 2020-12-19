package com.vapasi.biblioteca.response;

import java.util.Objects;

public class CustomerResponse {

    private String name;
    private String email;
    private String phone;

    public CustomerResponse(){
    }

    public CustomerResponse(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getPhone() { return phone; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerResponse that = (CustomerResponse) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone);
    }
}
