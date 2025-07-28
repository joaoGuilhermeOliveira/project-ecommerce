package com.ecommerce.store.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.store.services.BrandServiceImpl;
import com.ecommerce.store.web.dtos.requests.BrandRequestDto;
import com.ecommerce.store.web.dtos.responses.BrandResponseDto;

@RestController
@RequestMapping("/brands")
public class BrandController {
    
    private final BrandServiceImpl brandServiceImpl;

    @Autowired
    public BrandController(BrandServiceImpl brandServiceImpl) {
        this.brandServiceImpl = brandServiceImpl;
    }

    @PostMapping
    public ResponseEntity<String> createBrand(@RequestBody BrandRequestDto brandRequestDto) {
        brandServiceImpl.createBrand(brandRequestDto);
        return ResponseEntity.status(201).body("Brand created successfully");
    }

    @GetMapping
    public ResponseEntity<BrandResponseDto> getBrandById(@RequestParam Long id) {
        BrandResponseDto response = brandServiceImpl.getBrandById(id);
        return ResponseEntity.ok(response);
    }
}
