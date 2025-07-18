package com.ecommerce.store.services;

import org.springframework.stereotype.Service;

import com.ecommerce.store.entities.Brand;
import com.ecommerce.store.repositories.BrandRepository;
import com.ecommerce.store.services.mapper.BrandMapper;
import com.ecommerce.store.web.dtos.requests.BrandRequestDto;
import com.ecommerce.store.web.dtos.responses.BrandResponseDto;

@Service
public class BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public BrandService(BrandRepository brandRepository, BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }

    public void createBrand(BrandRequestDto brandRequestDto) {
        Brand brand = brandMapper.toEntity(brandRequestDto);
        brandRepository.save(brand);
    }

    public BrandResponseDto getBrandById(Long id) {
        Brand brand = brandRepository.findById(id).orElse(null);
        return brandMapper.toDto(brand);
    }
}
