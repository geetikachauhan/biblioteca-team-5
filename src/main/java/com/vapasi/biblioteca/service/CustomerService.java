package com.vapasi.biblioteca.service;

import com.vapasi.biblioteca.model.Customer;
import com.vapasi.biblioteca.repository.CustomerRepository;
import com.vapasi.biblioteca.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("userDetailService")
public class CustomerService implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse customerDetails(String libraryNumber) {
        Customer customer = customerRepository.findByLibraryNumber(libraryNumber);
        return new CustomerResponse(customer.getName(), customer.getEmail() , customer.getPhone());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String libraryNumber){
        Customer customer = customerRepository.findByLibraryNumber(libraryNumber);
        if (customer != null)
            return new User(customer.getLibraryNumber(),customer.getPassword(), buildSimpleGrantedAuthorities("ROLE_USER"));
        else throw new BadCredentialsException("User Not Found");
    }

    private static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(String role) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }


}
