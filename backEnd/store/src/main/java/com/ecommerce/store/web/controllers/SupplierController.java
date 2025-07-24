package com.ecommerce.store.web.controllers;

import com.ecommerce.store.entities.Supplier;
import com.ecommerce.store.services.SupplierServiceImpl;
import com.ecommerce.store.services.mapper.SupplierMapper;
import com.ecommerce.store.web.dtos.requests.SupplierRequestDto;
import com.ecommerce.store.web.dtos.responses.SupplierResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/supliers")
public class SupplierController {

    private final SupplierServiceImpl supplierServiceImpl;
    private final SupplierMapper supplierMapper;

    @Autowired
    public SupplierController(SupplierServiceImpl supplierServiceImpl) {
        this.supplierServiceImpl = supplierServiceImpl;
        this.supplierMapper = new SupplierMapper();
    }
    @PostMapping
    public ResponseEntity<String> createSupplier(@RequestBody SupplierRequestDto supplierCreateDto) {
        supplierServiceImpl.createSupplier(supplierCreateDto);
        return ResponseEntity.status(201).body("Supplier created successfully");
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<SupplierResponseDto> getUserByCnpj(@PathVariable String cnpj) {
        Supplier supplier = supplierServiceImpl.getSupplierByCnpj(cnpj);
        if (supplier == null) {
            return ResponseEntity.notFound().build();
        }
        SupplierResponseDto response = supplierMapper.toDto(supplier);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<String> deleteSupplier(@PathVariable String cnpj) {
        Supplier supplier = supplierServiceImpl.getSupplierByCnpj(cnpj);
        if (supplier == null) {
            return ResponseEntity.notFound().build();
        }
            supplierServiceImpl.deleteSupplierByCnpj(cnpj);
        return ResponseEntity.ok("Supplier deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponseDto>> getAllSuppliers() {
        List<Supplier> suppliers = supplierServiceImpl.getAllSuppliers();
        List<SupplierResponseDto> response = suppliers.stream()
                .map(supplierMapper::toDto)
                .toList();
        return ResponseEntity.ok(response);
    }
}
