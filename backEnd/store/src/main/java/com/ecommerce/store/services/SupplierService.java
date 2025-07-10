package com.ecommerce.store.services;

import com.ecommerce.store.entities.Supplier;
import com.ecommerce.store.exceptions.supplier.SupplierNotFoundException;
import com.ecommerce.store.repositories.SupplierRepository;
import com.ecommerce.store.services.mapper.SupplierMapper;
import com.ecommerce.store.web.dtos.request.SupplierDto;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    @Transient
    public Supplier createSupplier(SupplierDto supplierDto) {
        SupplierMapper mapper = new SupplierMapper();
        Supplier supplier = mapper.toEntity(supplierDto);
        return supplierRepository.save(supplier);
    }

    public Supplier getSupplierByCnpj(String cnpj) {
        return supplierRepository.findByCnpj(cnpj).orElseThrow(() -> new SupplierNotFoundException("Fornecedor não encontrado com CNPJ: " + cnpj));
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public void deleteSupplier(Supplier supplier) {
        supplierRepository.delete(supplier);
    }

    
}
