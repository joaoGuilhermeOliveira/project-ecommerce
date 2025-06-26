package com.ecommerce.store.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
