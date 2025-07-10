package com.ecommerce.store.services.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.store.entities.Category;
import com.ecommerce.store.entities.Product;
import com.ecommerce.store.repositories.CategoryRepository;
import com.ecommerce.store.repositories.SupplierRepository;
import com.ecommerce.store.entities.Supplier;
import com.ecommerce.store.web.dtos.request.CategoryDto;
import com.ecommerce.store.web.dtos.request.ProductDto;
import com.ecommerce.store.web.dtos.request.SupplierCreateDto;

@Component
public class ProductMapper {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SupplierRepository supplierRepository;

    public Product toEntity(ProductDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setCostPrice(productRequestDto.getCostPrice());
        product.setSellPrice(productRequestDto.getSellPrice());
        Category category = categoryRepository.findById(productRequestDto.getCategory().getId()).orElseThrow();
        product.setCategory(category);
        Supplier supplier = supplierRepository.findById(productRequestDto.getSupplier().getId()).orElseThrow();
        product.setSupplier(supplier);
        product.setImage(productRequestDto.getImage());
        product.setGtin(productRequestDto.getGtin());

        return product;
    }

    public ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setCostPrice(product.getCostPrice());
        productDto.setSellPrice(product.getSellPrice());
        productDto.setCategory(this.buildCategoryDto(product.getCategory()));
        productDto.setSupplier(this.buildSupplierDto(product.getSupplier()));
        productDto.setImage(product.getImage());
        productDto.setGtin(product.getGtin());

        return productDto;
    }

    private CategoryDto buildCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    private SupplierCreateDto buildSupplierDto(Supplier supplier) {
        SupplierCreateDto supplierDto = new SupplierCreateDto();
        supplierDto.setId(supplier.getSupplierId());
        supplierDto.setCnpj(supplier.getCnpj());
        supplierDto.setName(supplier.getName());
        supplierDto.setPhone_number(supplier.getPhone_number());
        supplierDto.setEmail(supplier.getEmail());
        return supplierDto;
    }
}
