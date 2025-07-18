package com.ecommerce.store.web.dtos.requests;

import java.sql.Blob;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String name;
    private String costPrice;
    private String sellPrice;
    private String description;
    private Long categoryId;
    private Long supplierId;
    private Blob image;
    private String gtin;
    private Long brandId;
}
