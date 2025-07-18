package com.ecommerce.store.services;

import com.ecommerce.store.entities.Supplier;
import com.ecommerce.store.enums.StatusEnum;
import com.ecommerce.store.repositories.SupplierRepository;
import com.ecommerce.store.services.mapper.SupplierMapper;
import com.ecommerce.store.web.dtos.requests.SupplierRequestDto;

import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    @Transient
    public Supplier createSupplier(SupplierRequestDto supplierCreateDto) {
        SupplierMapper mapper = new SupplierMapper();
        Supplier supplier = mapper.toEntity(supplierCreateDto);
        supplier.setStatus(StatusEnum.ACTIVE);
        return supplierRepository.save(supplier);
    }

    public Supplier getSupplierByCnpj(String cnpj) {
        return supplierRepository.findByCnpj(cnpj);
    }

    public void deleteSupplier(Supplier supplier) {
        supplierRepository.delete(supplier);
    }

}
