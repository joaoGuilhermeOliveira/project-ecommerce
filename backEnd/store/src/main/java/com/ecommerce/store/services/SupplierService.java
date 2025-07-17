package com.ecommerce.store.services;

import com.ecommerce.store.entities.Supplier;
import com.ecommerce.store.web.dtos.requests.SupplierRequestDto;

import java.util.List;

public interface SupplierService {
    Supplier createSupplier(SupplierRequestDto supplierRequestDto);

    Supplier getSupplierByCnpj(String cnpj);

    List<Supplier> getAllSuppliers();

    void deleteSupplierByCnpj(String cnpj);
}
