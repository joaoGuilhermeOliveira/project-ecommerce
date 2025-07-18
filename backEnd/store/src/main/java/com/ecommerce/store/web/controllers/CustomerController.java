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

import com.ecommerce.store.services.CustomerService;
import com.ecommerce.store.web.dtos.requests.CustomerRequestDto;
import com.ecommerce.store.web.dtos.responses.CustomerResponseDto;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody CustomerRequestDto customer) {
        customerService.createCustomer(customer);
        return ResponseEntity.status(201).body("Customer created successfully!");
    }

    @GetMapping
    public ResponseEntity<CustomerResponseDto> getCustomerByCpf(@RequestParam String cpf) {
        CustomerResponseDto response = customerService.getCustomerByCpf(cpf);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping
    public ResponseEntity<String> updateCustomerByCpf(@RequestParam String cpf, @RequestBody CustomerRequestDto updateCustomer) {
        customerService.updateCustomerByCpf(cpf, updateCustomer);
        
        return ResponseEntity.status(200).body("Customer updated successfully!");
    }
}
