package com.ecommerce.store.services;

import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.entities.Sale;
import com.ecommerce.store.repositories.CustomerRepository;
import com.ecommerce.store.repositories.SaleRepository;
import com.ecommerce.store.services.mapper.SaleMapper;
import com.ecommerce.store.web.dtos.request.SaleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SaleMapper saleMapper;

    public Sale createSale(SaleDto saleDto) {
        Customer customer = customerRepository.findById(saleDto.getCustomer().getCustomerId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Sale sale = saleMapper.toEntity(saleDto);
        sale.setCustomer(customer); // agora o customer está completo
        return saleRepository.save(sale);
    }
}
