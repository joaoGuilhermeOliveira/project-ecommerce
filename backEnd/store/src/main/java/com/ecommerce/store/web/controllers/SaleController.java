package com.ecommerce.store.web.controllers;

import com.ecommerce.store.entities.Sale;
import com.ecommerce.store.services.SaleService;
import com.ecommerce.store.services.mapper.SaleMapper;
import com.ecommerce.store.web.dtos.requests.SaleRequestDto;
import com.ecommerce.store.web.dtos.responses.SaleResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<SaleResponseDto> createSale(@RequestBody SaleRequestDto saleRequestDto) {
        Sale sale = saleService.createSale(saleRequestDto);
        SaleResponseDto response = saleMapper.toDto(sale);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<SaleResponseDto> getSaleById(@PathVariable Long saleId) {
        Sale sale = saleService.getSaleById(saleId);
        if (sale == null) {
            return ResponseEntity.notFound().build();
        }
        SaleResponseDto response = saleMapper.toDto(sale);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<SaleResponseDto>> getSales(){
        List<Sale> sales = saleService.getAllSales();
        List<SaleResponseDto> response = sales.stream()
                .map(saleMapper::toDto)
                .toList();
        return ResponseEntity.ok(response);
    }


}