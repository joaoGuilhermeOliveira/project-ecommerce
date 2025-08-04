package com.ecommerce.store.web.dtos.responses;

import lombok.Data;

@Data
public class ProductHasSaleResponseDto {

    private Long productId;
    private String productName;
    private Integer quantity;
    private String unityPrice;
    private String paymentMethod;

}
