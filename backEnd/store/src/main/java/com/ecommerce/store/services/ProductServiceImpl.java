package com.ecommerce.store.services;

import com.ecommerce.store.web.dtos.requests.UpdateStatusRequestDto;
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
        } catch (NotFoundException e) {
            throw new InvalidEntityException("Error creating product: Related entity not found." + e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new InvalidEntityException("Error creating product: " + e.getMessage());
        }
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with ID: " + id));
        BrandResponseDto brand = brandService.getBrandById(product.getBrand().getId());
        CategoryResponseDto category = categoryService.getCategoryById(product.getCategory().getId());
        SupplierResponseDto supplier = supplierService.getSupplierById(product.getSupplier().getId());

        ProductResponseDto response = productMapper.toDto(product);
        response.setBrand(brand);
        response.setCategory(category);
        response.setSupplier(supplier);
        return response;
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
