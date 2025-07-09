package com.ecommerce.store.services.mapper;



import com.ecommerce.store.entities.Supplier;
import com.ecommerce.store.web.dtos.request.SupplierDto;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {
    public Supplier toEntity(SupplierDto dto) {
        Supplier supplier = new Supplier();
        supplier.setCnpj(dto.getCnpj());
        supplier.setName(dto.getName());
        supplier.setPhone_number(dto.getPhone_number());
        supplier.setEmail(dto.getEmail());
        supplier.setAddress(dto.getAddress());
        return supplier;

    }

    public SupplierDto toDto(Supplier entity) {
        SupplierDto dto = new SupplierDto();
        dto.setCnpj(entity.getCnpj());
        dto.setName(entity.getName());
        dto.setPhone_number(entity.getPhone_number());
        dto.setEmail(entity.getEmail());
        dto.setAddress(entity.getAddress());
        return dto;
    }
}