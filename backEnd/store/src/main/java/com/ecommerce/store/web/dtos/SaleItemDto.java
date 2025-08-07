package com.ecommerce.store.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleItemDto {
    private Long productId;
    private Integer quantity;
    private String unitPrice;
}
