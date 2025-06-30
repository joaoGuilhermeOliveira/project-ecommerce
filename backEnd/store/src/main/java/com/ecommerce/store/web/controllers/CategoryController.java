package com.ecommerce.store.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.store.services.CategoryService;
import com.ecommerce.store.services.mapper.CategoryMapper;
import com.ecommerce.store.web.dtos.request.CategoryDto;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(CategoryService categoryService ) {
        this.categoryService = categoryService;
        this.categoryMapper = new CategoryMapper();
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.createCategory(categoryMapper.toEntity(categoryDto));
        return ResponseEntity.status(201).body("Category created successfully");
    }

    @GetMapping
    public ResponseEntity<CategoryDto> getCategoryById(@RequestParam Long id) {
        CategoryDto response = categoryMapper.toDto(categoryService.getCategoryById(id));
        return ResponseEntity.ok().body(response);
    }

}
