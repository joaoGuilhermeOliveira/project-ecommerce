package com.ecommerce.store.services.mapper;

import org.springframework.stereotype.Component;

import com.ecommerce.store.entities.Category;
import com.ecommerce.store.web.dtos.request.CategoryDto;

@Component
public class CategoryMapper {
    
    public Category toEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setId(categoryDto.getId());

        return category;
    }

    public CategoryDto toDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        
        return categoryDto;
    }

}
