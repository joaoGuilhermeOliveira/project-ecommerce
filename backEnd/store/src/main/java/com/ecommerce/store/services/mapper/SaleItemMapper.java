package com.ecommerce.store.services.mapper;

import com.ecommerce.store.entities.SaleItem;
import com.ecommerce.store.web.dtos.SaleItemDto;
import org.springframework.stereotype.Component;

@Component
public class SaleItemMapper {

    public SaleItem toEntity(SaleItemDto dto) {
        SaleItem entity = new SaleItem();
        entity.setQuantity(dto.getQuantity());
        entity.setUnitPrice(dto.getUnitPrice().toString());
        return entity;
    }

    public SaleItemDto toDto(SaleItem entity) {
        SaleItemDto dto = new SaleItemDto();
        dto.setProductId(entity.getProduct().getId());
        dto.setQuantity(entity.getQuantity());
        dto.setUnitPrice(entity.getUnitPrice());
        return dto;
    }
}