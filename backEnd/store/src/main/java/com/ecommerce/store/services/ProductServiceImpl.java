package com.ecommerce.store.services;

import com.ecommerce.store.web.dtos.requests.UpdateStatusRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ecommerce.store.entities.Brand;
import com.ecommerce.store.entities.Category;
import com.ecommerce.store.entities.Product;
import com.ecommerce.store.entities.Supplier;
import com.ecommerce.store.enums.ProductStatusEnum;
import com.ecommerce.store.exceptions.InvalidEntityException;
import com.ecommerce.store.exceptions.NotFoundException;
import com.ecommerce.store.repositories.ProductRepository;
import com.ecommerce.store.services.mapper.ProductMapper;
import com.ecommerce.store.web.dtos.requests.ProductRequestDto;
import com.ecommerce.store.web.dtos.responses.BrandResponseDto;
import com.ecommerce.store.web.dtos.responses.CategoryResponseDto;
import com.ecommerce.store.web.dtos.responses.ProductResponseDto;
import com.ecommerce.store.web.dtos.responses.SupplierResponseDto;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final SupplierService supplierService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper,
            CategoryService categoryService, BrandService brandService, SupplierService supplierService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.supplierService = supplierService;
    }

    @Override
    public void createProduct(ProductRequestDto productRequestDto) {
        log.info("Starting product creation for name: {}", productRequestDto.getName());

        try {
            Category category = categoryService.getCategoryEntityById(productRequestDto.getCategoryId());
            Brand brand = brandService.getBrandEntityById(productRequestDto.getBrandId());
            Supplier supplier = supplierService.getSupplierEntityById(productRequestDto.getSupplierId());

            Product product = productMapper.toEntity(productRequestDto);
            product.setCategory(category);
            product.setBrand(brand);
            product.setSupplier(supplier);
            product.setStatus(ProductStatusEnum.ACTIVE);
            productRepository.save(product);
            log.info("Product '{}' created successfully.", product.getName());

        } catch (NotFoundException e) {
            log.warn("Related entity not found while creating product: {}", e.getMessage());

            throw new InvalidEntityException("Error creating product: Related entity not found." + e.getMessage());
        } catch (DataIntegrityViolationException e) {
            log.error("Data integrity violation while creating product: {}", e.getMessage());

            throw new InvalidEntityException("Error creating product: " + e.getMessage());
        }
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        log.info("Fetching product with ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Product not found with ID: {}", id);
                    return new NotFoundException("Product not found with ID: " + id);
                });
        BrandResponseDto brand = brandService.getBrandById(product.getBrand().getId());
        CategoryResponseDto category = categoryService.getCategoryById(product.getCategory().getId());
        SupplierResponseDto supplier = supplierService.getSupplierById(product.getSupplier().getId());

        ProductResponseDto response = productMapper.toDto(product);
        response.setBrand(brand);
        response.setCategory(category);
        response.setSupplier(supplier);
        log.info("Product with ID {} fetched successfully.", id);

        return response;
    }

    @Override
    public void updateStatusById(String id, UpdateStatusRequestDto updateStatus) {
        log.info("Updating product status for ID: {}", id);

        Product product = productRepository.findById(Long.parseLong(id)).orElseThrow(() -> {
            log.warn("Product not found with ID: {}", id);
            return new NotFoundException("Product with ID " + id + " not found.");
        });

        if (updateStatus.getStatus() == null || updateStatus.getStatus().isBlank()) {
            log.warn("No status provided for product update. ID: {}", id);
            throw new InvalidEntityException("Status must be provided.");
        }

        if (product == null) {
            throw new IllegalArgumentException("Product with id " + id + " not found.");
        }

        ProductStatusEnum newStatus;
        try {
            newStatus = ProductStatusEnum.valueOf(updateStatus.getStatus().toUpperCase());
        } catch (IllegalArgumentException e) {
            log.warn("Invalid status '{}' provided for product ID: {}", updateStatus.getStatus(), id);
            throw new IllegalArgumentException("Invalid status: " + updateStatus.getStatus());
        }

        product.setStatus(newStatus);
        productRepository.save(product);
        log.info("Product ID {} status updated to {}", id, newStatus);

    }
}
