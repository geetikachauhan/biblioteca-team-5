package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.model.Customer;
import com.vapasi.biblioteca.repository.CustomerRepository;
import com.vapasi.biblioteca.response.CustomerResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;
    @InjectMocks
    CustomerService customerService;

    @Test
    void shouldReturnCustomerDetails() {
        Customer customer = new Customer(1 ,"test", "test", "test" ,"test@email.com","2342423456");
        when(customerRepository.findByLibraryNumber(anyString())).thenReturn(customer);
        CustomerResponse customerResponse = new CustomerResponse(customer.getName(),customer.getEmail(),customer.getPhone());
        assertEquals(customerResponse, customerService.customerDetails("test"));
        verify(customerRepository).findByLibraryNumber("test");


    }

    @Test
    void shouldReturnUserDetails() {
        Customer customer = new Customer(1 ,"test", "test", "test" ,"test@email.com","2342423456");
        when(customerRepository.findByLibraryNumber(anyString())).thenReturn(customer);
        CustomerResponse customerResponse = new CustomerResponse(customer.getName(),customer.getEmail(),customer.getPhone());
        assertEquals(customerResponse, customerService.customerDetails("test"));
        verify(customerRepository).findByLibraryNumber("test");


    }

}
