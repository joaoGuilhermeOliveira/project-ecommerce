package com.ecommerce.store.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.services.CustomerService;
import com.ecommerce.store.services.mapper.CustomerMapper;
import com.ecommerce.store.web.dtos.request.CustomerRequestDto;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
        this.customerMapper = new CustomerMapper();
    }

    @PostMapping
    public Customer createCustomer(@RequestBody CustomerRequestDto customer) {
        return customerService.createCustomer(customerMapper.toEntity(customer));
    }

    
}
