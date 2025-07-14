package com.ecommerce.store.services;

import com.ecommerce.store.entities.Supplier;
import com.ecommerce.store.exceptions.supplier.InvalidSupplierException;
import com.ecommerce.store.exceptions.supplier.SupplierNotFoundException;
import com.ecommerce.store.repositories.SupplierRepository;
import com.ecommerce.store.services.mapper.SupplierMapper;
import com.ecommerce.store.web.dtos.requests.SupplierDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public Supplier createSupplier(SupplierDto supplierDto) {
        try {
            SupplierMapper mapper = new SupplierMapper();
            Supplier supplier = mapper.toEntity(supplierDto);
            return supplierRepository.save(supplier);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidSupplierException("Erro ao criar fornecedor: " + e.getMessage());
        }
    }

    public Supplier getSupplierByCnpj(String cnpj) {
        return supplierRepository.findByCnpj(cnpj).orElseThrow(() -> new SupplierNotFoundException("Fornecedor não encontrado com CNPJ: " + cnpj));
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public void deleteSupplierByCnpj(String cnpj) {
        Supplier supplier = supplierRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new SupplierNotFoundException("Fornecedor não encontrado com CNPJ: " + cnpj));

        supplierRepository.delete(supplier);
    }
}
