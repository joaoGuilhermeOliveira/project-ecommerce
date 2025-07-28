package com.ecommerce.store.services;

import com.ecommerce.store.web.dtos.requests.ProductRequestDto;
import com.ecommerce.store.web.dtos.responses.ProductResponseDto;

public interface ProductService {
    void createProduct(ProductRequestDto productRequestDto);
    ProductResponseDto getProductById(Long id);
}
