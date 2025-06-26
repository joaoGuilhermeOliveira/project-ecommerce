package com.ecommerce.store.services;

import com.ecommerce.store.entities.Supplier;
import com.ecommerce.store.repositories.SupplierRepository;
import com.ecommerce.store.services.mapper.SupplierMapper;
import com.ecommerce.store.web.dtos.request.SupplierCreateRequestDto;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    @Transient
    public Supplier createSupplier(SupplierCreateRequestDto supplierCreateRequestDto) {
        SupplierMapper mapper = new SupplierMapper();
        Supplier supplier = mapper.toEntity(supplierCreateRequestDto);
        return supplierRepository.save(supplier);
    }
}
