package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.model.Customer;
import com.vapasi.biblioteca.repository.CustomerRepository;
import com.vapasi.biblioteca.response.CustomerResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;
    @InjectMocks
    CustomerService customerService;

    @Test
    void shouldReturnTheCustomerList() {
        List<Customer> expectedCustomerList = Arrays.asList(new Customer("Dave", "124-4567", "Dave@123"),
                new Customer("Ava", "456-5566", "Ava@123"));
        List<CustomerResponse> expectedCustomerResponseList = new ArrayList<>();
        expectedCustomerResponseList.add(new CustomerResponse("Dave", "124-4567", "Dave@123"));
        expectedCustomerResponseList.add(new CustomerResponse("Ava", "456-5566", "Ava@123"));
        when(customerRepository.findAll()).thenReturn(expectedCustomerList);
        List<CustomerResponse> actualCustomerResponseList = customerService.customerList();
        assertEquals(expectedCustomerResponseList, actualCustomerResponseList);
        verify(customerRepository).findAll();


    }


}
