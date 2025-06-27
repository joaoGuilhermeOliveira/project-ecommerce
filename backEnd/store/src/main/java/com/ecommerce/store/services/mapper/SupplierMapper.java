package com.ecommerce.store.services.mapper;



import com.ecommerce.store.entities.Supplier;
import com.ecommerce.store.web.dtos.request.SupplierCreateDto;

public class SupplierMapper {
    public Supplier toEntity(SupplierCreateDto dto) {
        Supplier supplier = new Supplier();
        supplier.setCnpj(dto.getCnpj());
        supplier.setName(dto.getName());
        supplier.setPhone_number(dto.getPhone_number());
        supplier.setEmail(dto.getEmail());
        supplier.setAddress(dto.getAddress());
        return supplier;

    }

    public SupplierCreateDto toDto(Supplier entity) {
        SupplierCreateDto dto = new SupplierCreateDto();
        dto.setCnpj(entity.getCnpj());
        dto.setName(entity.getName());
        dto.setPhone_number(entity.getPhone_number());
        dto.setEmail(entity.getEmail());
        dto.setAddress(entity.getAddress());
        return dto;
    }
}