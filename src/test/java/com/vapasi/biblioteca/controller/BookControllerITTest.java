package com.vapasi.biblioteca.controller;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class BookControllerITTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldListTheBooks() throws JSONException {
        String response = this.restTemplate.getForObject("/bibliotecadb/books/list", String.class);
        JSONAssert.assertEquals("[{id:1} , {id:2} ,{id:3} ,{id:4} ,{id:5} ,{id:6}]" , response , false);
    }

}
