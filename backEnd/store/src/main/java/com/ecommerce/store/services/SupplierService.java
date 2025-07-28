package com.ecommerce.store.services;

import com.ecommerce.store.entities.Supplier;
import com.ecommerce.store.web.dtos.requests.SupplierRequestDto;
import com.ecommerce.store.web.dtos.responses.SupplierResponseDto;

import java.util.List;

public interface SupplierService {
    void createSupplier(SupplierRequestDto supplierRequestDto);
    SupplierResponseDto getSupplierByCnpj(String cnpj);
    List<Supplier> getAllSuppliers();
    void deleteSupplierByCnpj(String cnpj);
    SupplierResponseDto getSupplierById(Long id);
    Supplier getSupplierEntityById(Long id);
}
