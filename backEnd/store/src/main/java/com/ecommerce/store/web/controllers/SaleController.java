package com.ecommerce.store.web.controllers;

import com.ecommerce.store.entities.Sale;
import com.ecommerce.store.services.SaleServiceImpl;
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

    private final SaleServiceImpl saleServiceImpl;
    private final SaleMapper saleMapper;

    @Autowired
    public SaleController(SaleServiceImpl saleServiceImpl) {
        this.saleServiceImpl = saleServiceImpl;
        this.saleMapper = new SaleMapper();
    }

    @PostMapping
    public ResponseEntity<SaleResponseDto> createSale(@RequestBody SaleRequestDto saleRequestDto) {
        SaleResponseDto response = saleServiceImpl.createSale(saleRequestDto);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<SaleResponseDto> getSaleById(@PathVariable Long saleId) {
        SaleResponseDto response = saleServiceImpl.getSaleById(saleId);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<SaleResponseDto>> getSales(){
        List<SaleResponseDto> responses = saleServiceImpl.getAllSales();
        return ResponseEntity.ok(responses);
    }


}