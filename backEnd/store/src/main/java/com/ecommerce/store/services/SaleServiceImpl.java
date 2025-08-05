package com.ecommerce.store.services;

import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.entities.Product;
import com.ecommerce.store.entities.Sale;
import com.ecommerce.store.entities.SaleItem;
import com.ecommerce.store.enums.SaleItemId;
import com.ecommerce.store.exceptions.InvalidEntityException;
import com.ecommerce.store.repositories.CustomerRepository;
import com.ecommerce.store.repositories.ProductHasSaleRepository;
import com.ecommerce.store.repositories.ProductRepository;
import com.ecommerce.store.repositories.SaleRepository;
import com.ecommerce.store.services.mapper.SaleItemMapper;
import com.ecommerce.store.services.mapper.SaleMapper;
import com.ecommerce.store.web.dtos.SaleItemDto;
import com.ecommerce.store.web.dtos.requests.SaleRequestDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService{

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SaleMapper saleMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductHasSaleRepository productHasSaleRepository;

    @Autowired
    private SaleItemMapper saleItemMapper;

    @Override
    @Transactional
    public Sale createSale(SaleRequestDto saleRequestDto) {
        Customer customer = customerRepository.findById(saleRequestDto.getCustomerId())
                .orElseThrow(() -> new InvalidEntityException("Customer not found: " + saleRequestDto.getCustomerId()));

        Sale sale = saleMapper.toEntity(saleRequestDto);
        sale.setSaleData(new Date());

        Sale savedSale = saleRepository.save(sale);

        for (SaleItemDto itemDto : saleRequestDto.getItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + itemDto.getProductId()));

            SaleItem item = saleItemMapper.toEntity(itemDto);

            item.setProduct(product);
            item.setSale(savedSale);
            item.setSubtotal(String.valueOf(item.getQuantity() * Double.parseDouble(item.getUnitPrice())));

            productHasSaleRepository.save(item);
        }

        return savedSale;
    }

    @Override
    public Sale getSaleById(Long saleId) {
        return saleRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Sale not found" + saleId));
    }

    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }
}