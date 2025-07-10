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
import com.ecommerce.store.services.mapper.ProductMapper;
import com.ecommerce.store.web.dtos.request.ProductDto;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductDto productDto) {
        productService.createProduct(productMapper.toEntity(productDto));
        return ResponseEntity.status(201).body("Product created successfully");
    }

    @GetMapping
    public ResponseEntity<ProductDto> getProductById(@RequestParam long id) {
        ProductDto response = productMapper.toDto(productService.getProductById(id));
        return ResponseEntity.ok(response);
    }
}
