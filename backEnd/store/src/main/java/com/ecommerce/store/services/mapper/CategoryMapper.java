package com.ecommerce.store.services.mapper;

import org.springframework.stereotype.Component;

import com.ecommerce.store.entities.Category;
import com.ecommerce.store.services.dtos.requests.CategoryRequestDto;
import com.ecommerce.store.services.dtos.responses.CategoryResponseDto;

@Component
public class CategoryMapper {
    
    public Category toEntity(CategoryRequestDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());

        return category;
    }

    public CategoryResponseDto toDto(Category category) {
        CategoryResponseDto categoryDto = new CategoryResponseDto();
        categoryDto.setName(category.getName());
        
        return categoryDto;
    }

}
