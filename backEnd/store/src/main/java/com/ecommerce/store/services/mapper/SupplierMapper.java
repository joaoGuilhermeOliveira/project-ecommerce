package com.ecommerce.store.services.mapper;



import com.ecommerce.store.entities.Supplier;
import com.ecommerce.store.web.dtos.request.SupplierCreateRequestDto;

public class SupplierMapper {
    public Supplier toEntity(SupplierCreateRequestDto dto) {
        Supplier supplier = new Supplier();
        supplier.setCnpj(dto.getCnpj());
        supplier.setName(dto.getName());
        supplier.setPhone_number(dto.getPhone_number());
        supplier.setEmail(dto.getEmail());
        supplier.setAddress(dto.getAddress());
        return supplier;

    }

    public SupplierCreateRequestDto toDto(Supplier entity) {
        SupplierCreateRequestDto dto = new SupplierCreateRequestDto();
        dto.setName(entity.getName());
        dto.setPhone_number(entity.getPhone_number());
        dto.setEmail(entity.getEmail());
        dto.setAddress(entity.getAddress());
        return dto;
    }
}