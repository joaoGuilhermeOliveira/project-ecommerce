package com.ecommerce.store.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.store.services.ProductService;
import com.ecommerce.store.services.dtos.requests.ProductRequestDto;
import com.ecommerce.store.services.dtos.responses.ProductResponseDto;
import com.ecommerce.store.services.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        productService.createProduct(productRequestDto);
        return ResponseEntity.status(201).body("Product created successfully");
    }

    @GetMapping
    public ResponseEntity<ProductResponseDto> getProductById(@RequestParam long id) {
        ProductResponseDto response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }
}
