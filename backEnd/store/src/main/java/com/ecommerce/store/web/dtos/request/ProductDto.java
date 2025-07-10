package com.ecommerce.store.web.dtos.request;

import java.sql.Blob;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private String costPrice;
    private String sellPrice;
    private String description;
    private CategoryDto category;
    private SupplierCreateDto supplier;
    private Blob image;
    private String gtin;
}
