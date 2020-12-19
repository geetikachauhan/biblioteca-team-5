package com.vapasi.biblioteca.repository;

import com.vapasi.biblioteca.model.Bookregister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRegisterRepository extends JpaRepository<Bookregister, Integer> {

}
