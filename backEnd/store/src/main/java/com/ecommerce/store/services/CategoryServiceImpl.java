package com.ecommerce.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ecommerce.store.entities.Category;
import com.ecommerce.store.exceptions.ConflictException;
import com.ecommerce.store.exceptions.InvalidEntityException;
import com.ecommerce.store.exceptions.NotFoundException;
import com.ecommerce.store.repositories.CategoryRepository;
import com.ecommerce.store.services.mapper.CategoryMapper;
import com.ecommerce.store.web.dtos.requests.CategoryRequestDto;
import com.ecommerce.store.web.dtos.responses.CategoryResponseDto;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public void createCategory(CategoryRequestDto categoryRequestDto) {
        if (categoryRequestDto == null || categoryRequestDto.getName() == null
                || categoryRequestDto.getName().isBlank()) {
            throw new InvalidEntityException("Category name must be provided.");
        }

        if (categoryRepository.existsByNameIgnoreCase(categoryRequestDto.getName())) {
            throw new ConflictException("Category already exists with name: " + categoryRequestDto.getName());
        }

        try {
            Category category = categoryMapper.toEntity(categoryRequestDto);
            categoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(
                    "Category violates unique constraints: " + e.getMostSpecificCause().getMessage());
        }
    }

    public CategoryResponseDto getCategoryById(Long id) {
        CategoryResponseDto response = categoryMapper.toDto(categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with ID " + id)));
        return response;
    }

    public Category getCategoryEntityById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));
    }
}
