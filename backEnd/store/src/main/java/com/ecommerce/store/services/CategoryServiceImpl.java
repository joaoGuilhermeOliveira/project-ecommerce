package com.ecommerce.store.services;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }
    @Override
    public void createCategory(CategoryRequestDto categoryRequestDto) {
        log.info("Starting category creation: {}", categoryRequestDto);

        if (categoryRequestDto == null || categoryRequestDto.getName() == null
                || categoryRequestDto.getName().isBlank()) {
            log.warn("Attempt to create category with invalid name: {}", categoryRequestDto);
            throw new InvalidEntityException("Category name must be provided.");
        }

        if (categoryRepository.existsByNameIgnoreCase(categoryRequestDto.getName())) {
            log.warn("Category already exists with name: {}", categoryRequestDto.getName());
            throw new ConflictException("Category already exists with name: " + categoryRequestDto.getName());
        }

        try {
            Category category = categoryMapper.toEntity(categoryRequestDto);
            categoryRepository.save(category);
            log.info("Category created successfully: {}", category.getName());

        } catch (DataIntegrityViolationException e) {
            log.error("Error saving category: {}", e.getMostSpecificCause().getMessage());

            throw new ConflictException(
                    "Category violates unique constraints: " + e.getMostSpecificCause().getMessage());
        }
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        log.info("Fetching category with ID: {}", id);

        CategoryResponseDto response = categoryMapper.toDto(categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Category not found with ID: {}", id);
                    return new NotFoundException("Category not found with ID " + id);
                }));
        log.info("Category found: {}", response.getName());

        return response;
    }

    @Override
    public Category getCategoryEntityById(Long id) {
        log.debug("Getting Category entity by ID: {}", id);

        return categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Category not found with ID: {}", id);
                    return new NotFoundException("Category not found");
                });
    }
}
