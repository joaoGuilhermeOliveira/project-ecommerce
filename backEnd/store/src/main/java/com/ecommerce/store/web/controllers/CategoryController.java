package com.ecommerce.store.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.store.services.CategoryServiceImpl;
import com.ecommerce.store.web.dtos.requests.CategoryRequestDto;
import com.ecommerce.store.web.dtos.responses.CategoryResponseDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryServiceImpl categoryServiceImpl;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody @Valid CategoryRequestDto categoryDto) {
        categoryServiceImpl.createCategory(categoryDto);
        return ResponseEntity.status(201).body("Category created successfully");
    }

    @GetMapping
    public ResponseEntity<CategoryResponseDto> getCategoryById(@RequestParam Long id) {
        CategoryResponseDto response = categoryServiceImpl.getCategoryById(id);
        return ResponseEntity.ok().body(response);
    }

}
