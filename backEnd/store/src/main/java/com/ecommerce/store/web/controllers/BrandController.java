package com.ecommerce.store.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.store.services.BrandService;
import com.ecommerce.store.services.dtos.requests.BrandRequestDto;
import com.ecommerce.store.services.dtos.responses.BrandResponseDto;

@RestController
@RequestMapping("/brands")
public class BrandController {
    
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping
    public ResponseEntity<String> createBrand(@RequestBody BrandRequestDto brandRequestDto) {
        brandService.createBrand(brandRequestDto);
        return ResponseEntity.status(201).body("Brand created successfully");
    }

    @GetMapping
    public ResponseEntity<BrandResponseDto> getBrandById(@RequestParam Long id) {
        BrandResponseDto response = brandService.getBrandById(id);
        return ResponseEntity.ok(response);
    }
}
