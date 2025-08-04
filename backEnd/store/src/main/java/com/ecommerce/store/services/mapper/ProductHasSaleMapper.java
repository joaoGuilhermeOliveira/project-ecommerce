package com.ecommerce.store.services.mapper;

import com.ecommerce.store.entities.SaleItem;
import com.ecommerce.store.web.dtos.requests.ProductHasSaleRequestDto;
import com.ecommerce.store.web.dtos.responses.ProductHasSaleResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ProductHasSaleMapper {

    public SaleItem toEntity(ProductHasSaleRequestDto dto) {
        SaleItem entity = new SaleItem();
        entity.setQuantity(dto.getQuantity());
        entity.setUnityPrice(dto.getUnitPrice() != null ? dto.getUnitPrice().toString() : "0"); // valor padrão
        entity.setPaymentMethod(dto.getPaymentMethod());
        return entity;
    }

    public ProductHasSaleResponseDto toDto(SaleItem entity) {
        ProductHasSaleResponseDto dto = new ProductHasSaleResponseDto();
        dto.setProductId(entity.getProduct().getId());
        dto.setProductName(entity.getProduct().getName());
        dto.setQuantity(entity.getQuantity());
        dto.setUnityPrice(entity.getUnityPrice());
        dto.setPaymentMethod(entity.getPaymentMethod());
        return dto;
    }
}