package com.ecommerce.store.services;

import com.ecommerce.store.entities.Category;
import com.ecommerce.store.web.dtos.requests.CategoryRequestDto;
import com.ecommerce.store.web.dtos.responses.CategoryResponseDto;

public interface CategoryService {
    void createCategory(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto getCategoryById(Long id);
    Category getCategoryEntityById(Long id);
}
