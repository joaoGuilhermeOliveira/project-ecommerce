package com.ecommerce.store.web.controllers;

import com.ecommerce.store.web.dtos.responses.CustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.services.CustomerServiceImpl;
import com.ecommerce.store.services.mapper.CustomerMapper;
import com.ecommerce.store.web.dtos.requests.CustomerRequestDto;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerServiceImpl customerServiceImpl;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerController(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
        this.customerMapper = new CustomerMapper();
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody CustomerRequestDto customer) {
        customerServiceImpl.createCustomer(customerMapper.toEntity(customer));
        return ResponseEntity.status(201).body("Customer created successfully!");
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<CustomerResponseDto> getCustomerByCpf(@PathVariable String cpf) {
        CustomerResponseDto response = customerMapper.toDto(customerServiceImpl.getCustomerByCpf(cpf));
        return ResponseEntity.ok(response);
    }

    @PatchMapping
    public ResponseEntity<String> updateCustomerByCpf(@RequestParam String cpf, @RequestBody CustomerRequestDto updateCustomer) {
        Customer customer = customerMapper.toEntity(updateCustomer);
        customerServiceImpl.updateCustomerByCpf(cpf, customer);
        
        return ResponseEntity.status(200).body("Customer updated successfully!");
    }
    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        List<Customer> customers = customerServiceImpl.getAllCustomers();
        List<CustomerResponseDto> response = customers.stream()
                .map(customerMapper::toDto)
                .toList();
        return ResponseEntity.ok(response);
    }
}
