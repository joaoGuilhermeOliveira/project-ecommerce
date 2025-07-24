package com.ecommerce.store.services;

import com.ecommerce.store.web.dtos.requests.BrandRequestDto;
import com.ecommerce.store.web.dtos.responses.BrandResponseDto;

public interface BrandService {
    void createBrand(BrandRequestDto brandRequestDto);
    BrandResponseDto getBrandById(Long id);
}
