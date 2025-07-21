package com.ecommerce.store.web.controllers;

import com.ecommerce.store.entities.Supplier;
import com.ecommerce.store.services.SupplierService;
import com.ecommerce.store.services.mapper.SupplierMapper;
import com.ecommerce.store.web.dtos.requests.SupplierRequestDto;
import com.ecommerce.store.web.dtos.responses.SupplierResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/supliers")
public class SupplierController {

    private final SupplierService supplierService;
    private final SupplierMapper supplierMapper;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
        this.supplierMapper = new SupplierMapper();
    }
    @PostMapping
    public ResponseEntity<String> createSupplier(@RequestBody SupplierRequestDto supplierCreateDto) {
        supplierService.createSupplier(supplierCreateDto);
        return ResponseEntity.status(201).body("Supplier created successfully");
    }

    @GetMapping()
    public ResponseEntity<SupplierResponseDto> getUserByCnpj(@RequestParam String cnpj) {
        Supplier supplier = supplierService.getSupplierByCnpj(cnpj);
        if (supplier == null) {
            return ResponseEntity.notFound().build();
        }
        SupplierResponseDto response = supplierMapper.toDto(supplier);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<String> deleteSupplier(@PathVariable String cnpj) {
        Supplier supplier = supplierService.getSupplierByCnpj(cnpj);
        if (supplier == null) {
            return ResponseEntity.notFound().build();
        }
            supplierService.deleteSupplierByCnpj(cnpj);
        return ResponseEntity.ok("Supplier deleted successfully");
    }

}
