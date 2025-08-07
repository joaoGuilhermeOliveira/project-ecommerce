package com.ecommerce.store.services;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public BrandServiceImpl(BrandRepository brandRepository, BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }

    @Override
    public void createBrand(BrandRequestDto brandRequestDto) {
        log.info("Starting creation of new brand: {}", brandRequestDto);

        if (brandRequestDto == null || brandRequestDto.getName() == null || brandRequestDto.getName().isBlank()) {
            log.warn("Attempt to create brand with invalid name: {}", brandRequestDto);
            throw new InvalidEntityException("Brand name must be provided.");
        }

        if (brandRepository.existsByNameIgnoreCase(brandRequestDto.getName())) {
            log.warn("Brand already exists with name: {}", brandRequestDto.getName());
            throw new ConflictException("Brand already exists with name: " + brandRequestDto.getName());
        }

        try {
            Brand brand = brandMapper.toEntity(brandRequestDto);
            brandRepository.save(brand);
            log.info("Brand created successfully: {}", brand.getName());

        } catch (DataIntegrityViolationException e) {
            log.error("Error saving brand in the database: {}", e.getMessage());

            throw new ConflictException("Brand violates unique constraints: " + e.getMostSpecificCause().getMessage());
        }
    }

    @Override
    public BrandResponseDto getBrandById(Long id) {
        log.info("Fetching brand with ID: {}", id);

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Brand not found with ID: {}", id);
                    return new NotFoundException("Brand not found with ID: " + id);
                });

        log.info("Brand found: {}", brand.getName());
        return brandMapper.toDto(brand);
    }

    @Override
    public Brand getBrandEntityById(Long id) {
        log.debug("Getting Brand entity by ID: {}", id);

        return brandRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Brand not found with ID: {}", id);
                    return new NotFoundException("Brand not found with ID: " + id);
                });
    }
}
