package com.ecommerce.store.web.dtos.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductHasSaleRequestDto {
    private Long productId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private String paymentMethod;
}
