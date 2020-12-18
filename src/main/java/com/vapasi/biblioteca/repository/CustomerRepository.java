package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    public Customer findByLibraryNumber (String libraryNumber);

}
