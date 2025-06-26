package com.ecommerce.store.web.controllers;

import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.entities.Supplier;
import com.ecommerce.store.services.CustomerService;
import com.ecommerce.store.services.SupplierService;
import com.ecommerce.store.services.mapper.CustomerMapper;
import com.ecommerce.store.services.mapper.SupplierMapper;
import com.ecommerce.store.web.dtos.request.CustomerRequestDto;
import com.ecommerce.store.web.dtos.request.SupplierCreateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/supliers")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }
    @PostMapping
    public ResponseEntity<String> createSupplier(@RequestBody SupplierCreateRequestDto supplierCreateRequestDto) {
        Supplier response = supplierService.createSupplier(supplierCreateRequestDto);
        return ResponseEntity.status(201).body("Supplier created successfully");
    }

}
