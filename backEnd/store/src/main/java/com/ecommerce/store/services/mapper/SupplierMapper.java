package com.ecommerce.store.services.mapper;

import org.springframework.stereotype.Component;

import com.ecommerce.store.entities.Supplier;
import com.ecommerce.store.services.dtos.requests.SupplierRequestDto;
import com.ecommerce.store.services.dtos.responses.SupplierResponseDto;

@Component
public class SupplierMapper {
    public Supplier toEntity(SupplierRequestDto dto) {
        Supplier supplier = new Supplier();
        supplier.setCnpj(dto.getCnpj());
        supplier.setName(dto.getName());
        supplier.setPhone_number(dto.getPhone_number());
        supplier.setEmail(dto.getEmail());
        supplier.setAddress(dto.getAddress());
        return supplier;

    }

    public SupplierResponseDto toDto(Supplier entity) {
        SupplierResponseDto dto = new SupplierResponseDto();
        dto.setCnpj(entity.getCnpj());
        dto.setName(entity.getName());
        dto.setPhone_number(entity.getPhone_number());
        dto.setEmail(entity.getEmail());
        dto.setAddress(entity.getAddress());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}