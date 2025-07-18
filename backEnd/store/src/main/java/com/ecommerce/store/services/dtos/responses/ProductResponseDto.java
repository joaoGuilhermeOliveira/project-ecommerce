package com.ecommerce.store.services.dtos.responses;

import java.sql.Blob;

import com.ecommerce.store.enums.ProductStatusEnum;

import lombok.Data;

@Data
public class ProductResponseDto {
    private String name;
    private String costPrice;
    private String sellPrice;
    private String description;
    private CategoryResponseDto category;
    private SupplierResponseDto supplier;
    private Blob image;
    private String gtin;
    private BrandResponseDto brand;
    private ProductStatusEnum status;
}
