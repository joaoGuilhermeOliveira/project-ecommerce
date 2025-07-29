package com.ecommerce.store.web.controllers;

import com.ecommerce.store.web.dtos.requests.UpdateStatusRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.store.services.ProductServiceImpl;
import com.ecommerce.store.web.dtos.requests.ProductRequestDto;
import com.ecommerce.store.web.dtos.responses.ProductResponseDto;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    @Autowired
    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        productServiceImpl.createProduct(productRequestDto);
        return ResponseEntity.status(201).body("Product created successfully");
    }

    @GetMapping
    public ResponseEntity<ProductResponseDto> getProductById(@RequestParam Long id) {
        ProductResponseDto response = productServiceImpl.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable String id,
                                             @RequestBody UpdateStatusRequestDto updateStatus) {
        productServiceImpl.updateStatusById(id, updateStatus);
        return ResponseEntity.noContent().build();
    }
}
