package com.ecommerce.store.services;

import com.ecommerce.store.web.dtos.requests.SaleRequestDto;
import com.ecommerce.store.web.dtos.responses.SaleResponseDto;

import java.util.List;

public interface SaleService {
    SaleResponseDto createSale(SaleRequestDto saleRequestDto);
    SaleResponseDto getSaleById(Long saleId);
    List<SaleResponseDto> getAllSales();
}
