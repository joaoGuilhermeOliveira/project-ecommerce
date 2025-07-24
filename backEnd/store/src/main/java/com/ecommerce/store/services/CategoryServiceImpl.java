package com.ecommerce.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.store.entities.Category;
import com.ecommerce.store.repositories.CategoryRepository;
import com.ecommerce.store.services.mapper.CategoryMapper;
import com.ecommerce.store.web.dtos.requests.CategoryRequestDto;
import com.ecommerce.store.web.dtos.responses.CategoryResponseDto;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public void createCategory(CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.toEntity(categoryRequestDto);
        categoryRepository.save(category);
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        CategoryResponseDto response = categoryMapper.toDto(categoryRepository.findById(id).orElse(null));
        return response;
    }
}
