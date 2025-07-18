package com.ecommerce.store.services.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.store.entities.Brand;
import com.ecommerce.store.entities.Category;
import com.ecommerce.store.entities.Product;
import com.ecommerce.store.repositories.BrandRepository;
import com.ecommerce.store.repositories.CategoryRepository;
import com.ecommerce.store.repositories.SupplierRepository;
import com.ecommerce.store.web.dtos.requests.ProductRequestDto;
import com.ecommerce.store.web.dtos.responses.CategoryResponseDto;
import com.ecommerce.store.web.dtos.responses.ProductResponseDto;
import com.ecommerce.store.web.dtos.responses.SupplierResponseDto;
import com.ecommerce.store.entities.Supplier;

@Component
public class ProductMapper {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private BrandRepository brandRepository;

    public Product toEntity(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setCostPrice(productRequestDto.getCostPrice());
        product.setSellPrice(productRequestDto.getSellPrice());
        Category category = categoryRepository.findById(productRequestDto.getCategoryId()).orElseThrow();
        product.setCategory(category);
        Supplier supplier = supplierRepository.findById(productRequestDto.getSupplierId()).orElseThrow();
        product.setSupplier(supplier);
        product.setImage(productRequestDto.getImage());
        product.setGtin(productRequestDto.getGtin());
        Brand brand = brandRepository.findById(productRequestDto.getBrandId()).orElseThrow();
        product.setBrand(brand);

        return product;
    }

    public ProductResponseDto toDto(Product product) {
        ProductResponseDto productDto = new ProductResponseDto();
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setCostPrice(product.getCostPrice());
        productDto.setSellPrice(product.getSellPrice());
        productDto.setCategory(this.buildCategoryDto(product.getCategory()));
        productDto.setSupplier(this.buildSupplierDto(product.getSupplier()));
        productDto.setImage(product.getImage());
        productDto.setGtin(product.getGtin());
        productDto.setStatus(product.getStatus());

        return productDto;
    }

    private CategoryResponseDto buildCategoryDto(Category category) {
        CategoryResponseDto categoryDto = new CategoryResponseDto();
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    private SupplierResponseDto buildSupplierDto(Supplier supplier) {
        SupplierResponseDto supplierDto = new SupplierResponseDto();
        supplierDto.setCnpj(supplier.getCnpj());
        supplierDto.setName(supplier.getName());
        supplierDto.setPhone_number(supplier.getPhone_number());
        supplierDto.setEmail(supplier.getEmail());
        return supplierDto;
    }

    
}
