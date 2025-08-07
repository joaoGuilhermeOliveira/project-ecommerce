package com.ecommerce.store.services;

import com.ecommerce.store.entities.Supplier;
import com.ecommerce.store.enums.StatusEnum;
import com.ecommerce.store.exceptions.ConflictException;
import com.ecommerce.store.exceptions.InvalidEntityException;
import com.ecommerce.store.exceptions.NotFoundException;
import com.ecommerce.store.repositories.SupplierRepository;
import com.ecommerce.store.services.mapper.SupplierMapper;
import com.ecommerce.store.web.dtos.requests.SupplierRequestDto;
import com.ecommerce.store.web.dtos.responses.SupplierResponseDto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SupplierServiceImpl implements SupplierService{
    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    private SupplierMapper supplierMapper;

    public void createSupplier(SupplierRequestDto supplierRequestDto) {
        log.info("Creating supplier with CNPJ: {}", supplierRequestDto.getCnpj());
        if(supplierRepository.existsByCnpj(supplierRequestDto.getCnpj())) {
            log.warn("Supplier creation failed: CNPJ {} already exists", supplierRequestDto.getCnpj());
            throw new ConflictException("Supplier with CNPJ " + supplierRequestDto.getCnpj() + " already exists.");
        }
        try {
            Supplier supplier = supplierMapper.toEntity(supplierRequestDto);
            supplier.setStatus(StatusEnum.ACTIVE);
            supplierRepository.save(supplier);
            log.info("Supplier created successfully with CNPJ: {}", supplierRequestDto.getCnpj());

        } catch (DataIntegrityViolationException e) {
            log.error("Data integrity violation when creating supplier with CNPJ {}: {}", supplierRequestDto.getCnpj(), e.getMessage());
            throw new InvalidEntityException("Error creating supplier: " + e.getMessage());
        }
    }

    public SupplierResponseDto getSupplierByCnpj(String cnpj) {
        log.info("Fetching supplier by CNPJ: {}", cnpj);
        Supplier supplier = supplierRepository.findByCnpj(cnpj)
                .orElseThrow(() -> {
                    log.warn("Supplier not found with CNPJ: {}", cnpj);
                    return new NotFoundException("Supplier not found with CNPJ: " + cnpj);
                });
        return supplierMapper.toDto(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        log.info("Fetching all suppliers");
        return supplierRepository.findAll();
    }

    public void deleteSupplierByCnpj(String cnpj) {
        log.info("Deleting supplier with CNPJ: {}", cnpj);
        Supplier supplier = supplierRepository.findByCnpj(cnpj)
                .orElseThrow(() -> {
                    log.warn("Supplier not found with CNPJ: {}", cnpj);
                    return new NotFoundException("Supplier not found with CNPJ: " + cnpj);
                });
        supplierRepository.delete(supplier);
        log.info("Supplier with CNPJ {} deleted successfully", cnpj);

    }

    public SupplierResponseDto getSupplierById(Long id) {
        log.info("Fetching supplier by ID: {}", id);
        Supplier supplier = getSupplierEntityById(id);
        return supplierMapper.toDto(supplier);
    }

    public Supplier getSupplierEntityById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Supplier not found with ID: {}", id);
                    return new NotFoundException("Supplier not found");
                });
    }
}