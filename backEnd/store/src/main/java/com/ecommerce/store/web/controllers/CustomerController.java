package com.ecommerce.store.web.controllers;

import com.ecommerce.store.web.dtos.requests.UpdateStatusRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.store.services.CustomerServiceImpl;
import com.ecommerce.store.web.dtos.requests.CustomerRequestDto;
import com.ecommerce.store.web.dtos.responses.CustomerResponseDto;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerServiceImpl customerServiceImpl;

    @Autowired
    public CustomerController(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody CustomerRequestDto customer) {
        customerServiceImpl.createCustomer(customer);
        return ResponseEntity.status(201).body("Customer created successfully!");
    }

    @GetMapping
    public ResponseEntity<CustomerResponseDto> getCustomerByCpf(@RequestParam String cpf) {
        CustomerResponseDto response = customerServiceImpl.getCustomerByCpf(cpf);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping
    public ResponseEntity<String> updateCustomerByCpf(@RequestParam String cpf, @RequestBody CustomerRequestDto updateCustomer) {
        customerServiceImpl.updateCustomerByCpf(cpf, updateCustomer);
        return ResponseEntity.status(200).body("Customer updated successfully!");
    }

    @PutMapping("/{cpf}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable String cpf,
                                             @RequestBody UpdateStatusRequestDto updateStatus) {
        customerServiceImpl.updateStatusByCpf(cpf, updateStatus);
        return ResponseEntity.noContent().build();
    }

}
