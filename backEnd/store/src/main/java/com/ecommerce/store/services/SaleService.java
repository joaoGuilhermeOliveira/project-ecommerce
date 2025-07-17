package com.ecommerce.store.services;

import com.ecommerce.store.entities.Sale;
import com.ecommerce.store.web.dtos.requests.SaleRequestDto;

import java.util.List;

public interface SaleService {

    Sale createSale(SaleRequestDto saleRequestDto);

    Sale getSaleById(Long saleId);

    List<Sale> getAllSales();
}
