package com.ecommerce.store.services;

import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import com.ecommerce.store.entities.Brand;
import com.ecommerce.store.exceptions.ConflictException;
import com.ecommerce.store.exceptions.InvalidEntityException;
import com.ecommerce.store.exceptions.NotFoundException;
import com.ecommerce.store.repositories.BrandRepository;
import com.ecommerce.store.services.mapper.BrandMapper;
import com.ecommerce.store.web.dtos.requests.BrandRequestDto;
import com.ecommerce.store.web.dtos.responses.BrandResponseDto;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public BrandServiceImpl(BrandRepository brandRepository, BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }

    @Override
    public void createBrand(BrandRequestDto brandRequestDto) {
        if (brandRequestDto == null || brandRequestDto.getName() == null || brandRequestDto.getName().isBlank()) {
            throw new InvalidEntityException("Brand name must be provided.");
        }

        if (brandRepository.existsByNameIgnoreCase(brandRequestDto.getName())) {
            throw new ConflictException("Brand already exists with name: " + brandRequestDto.getName());
        }

        try {
            Brand brand = brandMapper.toEntity(brandRequestDto);
            brandRepository.save(brand);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Brand violates unique constraints: " + e.getMostSpecificCause().getMessage());
        }
    }

    @Override
    public BrandResponseDto getBrandById(Long id) {
        Brand brand = brandRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Brand not found with ID: " + id));
        return brandMapper.toDto(brand);
    }

    @Override
    public Brand getBrandEntityById(Long id) {
        return brandRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Brand not found with ID: " + id));
    }
}
