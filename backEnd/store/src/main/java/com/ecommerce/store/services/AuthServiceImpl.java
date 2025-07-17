package com.ecommerce.store.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.repositories.CustomerRepository;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean authenticate(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        return customer != null && customer.getPassword().equals(password);
    }
}