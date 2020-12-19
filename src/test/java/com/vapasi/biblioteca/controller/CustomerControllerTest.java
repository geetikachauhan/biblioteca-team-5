package com.vapasi.biblioteca.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class CustomerControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    HttpEntity<String> entity;
    Long id;

    @BeforeEach
    public void setUp() {
        id = 2l;
        String requestBody = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("test", "test");
        entity = new HttpEntity<String>(requestBody, headers);
    }

    private final String CUSTOMER_DETAILS_URL = "/customer";


    @Test
    void shouldListTheCustomerDetails() {
        ResponseEntity<String> response = this.restTemplate.exchange(CUSTOMER_DETAILS_URL, HttpMethod.GET, entity, String.class, id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
