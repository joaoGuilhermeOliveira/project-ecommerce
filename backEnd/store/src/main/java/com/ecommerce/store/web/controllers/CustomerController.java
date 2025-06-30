package com.ecommerce.store.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.services.CustomerService;
import com.ecommerce.store.services.mapper.CustomerMapper;
import com.ecommerce.store.web.dtos.request.CustomerDto;

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
    public ResponseEntity<String> createCustomer(@RequestBody CustomerDto customer) {
        customerService.createCustomer(customerMapper.toEntity(customer));
        return ResponseEntity.status(201).body("Customer created successfully!");
    }

    @GetMapping
    public ResponseEntity<CustomerDto> getCustomerByCpf(@RequestParam String cpf) {
        CustomerDto response = customerMapper.toDto(customerService.getCustomerByCpf(cpf));
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping
    public ResponseEntity<String> updateCustomerByCpf(@RequestParam String cpf, @RequestBody CustomerDto updateCustomer) {
        Customer customer = customerMapper.toEntity(updateCustomer);
        customerService.updateCustomerByCpf(cpf, customer);
        
        return ResponseEntity.status(200).body("Customer updated successfully!");
    }
}
