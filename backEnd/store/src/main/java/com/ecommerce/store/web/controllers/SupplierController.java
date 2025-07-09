package com.ecommerce.store.web.controllers;

import com.ecommerce.store.entities.Supplier;
import com.ecommerce.store.services.SupplierService;
import com.ecommerce.store.services.mapper.SupplierMapper;
import com.ecommerce.store.web.dtos.request.SupplierDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<String> createSupplier(@RequestBody SupplierDto supplierDto) {
        Supplier response = supplierService.createSupplier(supplierDto);
        return ResponseEntity.status(201).body("Supplier created successfully");
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<SupplierDto> getUserByCnpj(@PathVariable String cnpj) {
        Supplier supplier = supplierService.getSupplierByCnpj(cnpj);
        if (supplier == null) {
            return ResponseEntity.notFound().build();
        }
        SupplierDto response = supplierMapper.toDto(supplier);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<List<SupplierDto>> getSupplierAll() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        List<SupplierDto> response = suppliers.stream()
                .map(supplierMapper::toDto)
                .toList();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<String> deleteSupplier(@PathVariable String cnpj) {
        Supplier supplier = supplierService.getSupplierByCnpj(cnpj);
        if (supplier == null) {
            return ResponseEntity.notFound().build();
        }
            supplierService.deleteSupplier(supplier);
        return ResponseEntity.ok("Supplier deleted successfully");
    }

}
