package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.model.Customer;
import com.vapasi.biblioteca.repository.CustomerRepository;
import com.vapasi.biblioteca.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("userDetailService")
public class CustomerService implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }

    public CustomerResponse customerDetails(String libraryNumber) {
        Customer customer = customerRepository.findByLibraryNumber(libraryNumber);
        CustomerResponse customerResponse = new CustomerResponse(customer.getName(), customer.getLibraryNumber(), customer.getPassword());
        return customerResponse;
   }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String libraryNumber) throws RuntimeException{
        Customer customer = customerRepository.findByLibraryNumber(libraryNumber);
        System.out.println(libraryNumber);
        if (customer != null)
            return new User(customer.getLibraryNumber(),customer.getPassword(), buildSimpleGrantedAuthorities("ROLE_USER"));
        else
            throw new RuntimeException("User not found");
    }

    private static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(String role) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }


}
