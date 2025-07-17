package com.ecommerce.store.services;

import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.entities.Sale;
import com.ecommerce.store.repositories.CustomerRepository;
import com.ecommerce.store.repositories.SaleRepository;
import com.ecommerce.store.services.mapper.SaleMapper;
import com.ecommerce.store.web.dtos.requests.SaleRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService{

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SaleMapper saleMapper;

    @Override
    public Sale createSale(SaleRequestDto saleRequestDto) {
        Customer customer = customerRepository.findById(saleRequestDto.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Sale sale = saleMapper.toEntity(saleRequestDto);
        sale.setCustomer(customer);
        return saleRepository.save(sale);
    }

    @Override
    public Sale getSaleById(Long saleId) {
        return saleRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
    }

    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }
}
