package com.ecommerce.store.services;

import com.ecommerce.store.entities.Supplier;
import com.ecommerce.store.enums.StatusEnum;
import com.ecommerce.store.exceptions.InvalidEntityException;
import com.ecommerce.store.exceptions.NotFoundException;
import com.ecommerce.store.repositories.SupplierRepository;
import com.ecommerce.store.services.mapper.SupplierMapper;
import com.ecommerce.store.web.dtos.requests.SupplierRequestDto;
import com.ecommerce.store.web.dtos.responses.SupplierResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService{
    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    private SupplierMapper supplierMapper;

    public void createSupplier(SupplierRequestDto supplierRequestDto) {
        try {
            Supplier supplier = supplierMapper.toEntity(supplierRequestDto);
            supplier.setStatus(StatusEnum.ACTIVE);
            supplierRepository.save(supplier);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidEntityException("Erro ao criar fornecedor: " + e.getMessage());
        }
    }

    public SupplierResponseDto getSupplierByCnpj(String cnpj) {
        Supplier supplier = supplierRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new NotFoundException("Fornecedor não encontrado com CNPJ: " + cnpj));
        return supplierMapper.toDto(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public void deleteSupplierByCnpj(String cnpj) {
        Supplier supplier = supplierRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new NotFoundException("Fornecedor não encontrado com CNPJ: " + cnpj));

        supplierRepository.delete(supplier);
    }

    public SupplierResponseDto getSupplierById(Long id) {
        Supplier supplier = getSupplierEntityById(id);
        return supplierMapper.toDto(supplier);
    }

    public Supplier getSupplierEntityById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fornecedor não encontrado"));
    }
}