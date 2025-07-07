package com.ecommerce.store.web.controllers;

import com.ecommerce.store.entities.Sale;
import com.ecommerce.store.services.SaleService;
import com.ecommerce.store.services.mapper.SaleMapper;
import com.ecommerce.store.web.dtos.request.SaleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/sales")
public class SaleController {

    private final SaleService saleService;
    private final SaleMapper saleMapper;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
        this.saleMapper = new SaleMapper();
    }

    @PostMapping
    public ResponseEntity<SaleDto> createSale(@RequestBody SaleDto saleDto) {
        Sale sale = saleService.createSale(saleDto);
        SaleDto response = saleMapper.toDto(sale);
        return ResponseEntity.status(201).body(response);
    }
}
