package com.vapasi.biblioteca.controller;

import com.vapasi.biblioteca.response.CustomerResponse;
import com.vapasi.biblioteca.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Timestamp;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("")
    public ResponseEntity<CustomerResponse> customerDetails() {
        String libraryNumber = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("[Customer-Details-Action] " + libraryNumber + ": " + new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok().body(customerService.customerDetails(libraryNumber));
    }

}
