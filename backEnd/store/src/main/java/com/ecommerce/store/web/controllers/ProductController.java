package com.ecommerce.store.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
