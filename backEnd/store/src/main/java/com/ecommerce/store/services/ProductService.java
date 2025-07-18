package com.ecommerce.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.store.entities.Product;
import com.ecommerce.store.enums.ProductStatusEnum;
import com.ecommerce.store.repositories.ProductRepository;
import com.ecommerce.store.services.mapper.ProductMapper;
import com.ecommerce.store.web.dtos.requests.ProductRequestDto;
import com.ecommerce.store.web.dtos.responses.ProductResponseDto;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public void createProduct(ProductRequestDto productRequestDto) {
        Product product = productMapper.toEntity(productRequestDto);
        product.setStatus(ProductStatusEnum.ACTIVE);
        productRepository.save(product);
    }

    public ProductResponseDto getProductById(Long id) {
        ProductResponseDto response = productMapper.toDto(productRepository.findById(id).orElse(null));
        return response;
    }
}
