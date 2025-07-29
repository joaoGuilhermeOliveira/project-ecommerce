package com.ecommerce.store.services;

import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.enums.StatusEnum;
import com.ecommerce.store.web.dtos.requests.UpdateStatusRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.store.entities.Product;
import com.ecommerce.store.enums.ProductStatusEnum;
import com.ecommerce.store.repositories.ProductRepository;
import com.ecommerce.store.services.mapper.ProductMapper;
import com.ecommerce.store.web.dtos.requests.ProductRequestDto;
import com.ecommerce.store.web.dtos.responses.ProductResponseDto;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void createProduct(ProductRequestDto productRequestDto) {
        Product product = productMapper.toEntity(productRequestDto);
        product.setStatus(ProductStatusEnum.ACTIVE);
        productRepository.save(product);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        return productMapper.toDto(productRepository.findById(id).orElse(null));
    }

    @Override
    public void updateStatusById(String id, UpdateStatusRequestDto updateStatus) {

        Product product = productRepository.findById(Long.parseLong(id)).orElseThrow(() -> new RuntimeException("Product not found"));

        if (product == null) {
            throw new IllegalArgumentException("Product with id " + id + " not found.");
        }

        if (updateStatus.getStatus() == null) {
            throw new IllegalArgumentException("Status must be provided.");
        }

        ProductStatusEnum newStatus;
        try {
            newStatus = ProductStatusEnum.valueOf(updateStatus.getStatus().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status: " + updateStatus.getStatus());
        }

        product.setStatus(newStatus);
        productRepository.save(product);
    }
}
