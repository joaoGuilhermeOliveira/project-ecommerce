package com.ecommerce.store.services.mapper;

import org.springframework.stereotype.Component;

import com.ecommerce.store.entities.Brand;
import com.ecommerce.store.web.dtos.requests.BrandRequestDto;
import com.ecommerce.store.web.dtos.responses.BrandResponseDto;

@Component
public class BrandMapper {
    
    public Brand toEntity(BrandRequestDto brandDto) {
        Brand brand = new Brand();
        brand.setName(brandDto.getName());

        return brand;
    }

    public BrandResponseDto toDto(Brand brand) {
        BrandResponseDto brandDto = new BrandResponseDto();
        brandDto.setName(brand.getName());
        
        return brandDto;
    }
}
