package com.ecommerce.store.services;

import com.ecommerce.store.entities.Product;
import com.ecommerce.store.entities.Sale;
import com.ecommerce.store.entities.SaleItem;
import com.ecommerce.store.exceptions.InvalidEntityException;
import com.ecommerce.store.repositories.CustomerRepository;
import com.ecommerce.store.repositories.ProductHasSaleRepository;
import com.ecommerce.store.repositories.ProductRepository;
import com.ecommerce.store.repositories.SaleRepository;
import com.ecommerce.store.services.mapper.SaleItemMapper;
import com.ecommerce.store.services.mapper.SaleMapper;
import com.ecommerce.store.web.dtos.SaleItemDto;
import com.ecommerce.store.web.dtos.requests.SaleRequestDto;
import com.ecommerce.store.web.dtos.responses.SaleResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public SaleResponseDto createSale(SaleRequestDto saleRequestDto) {
        customerRepository.findById(saleRequestDto.getCustomerId())
                .orElseThrow(() -> new InvalidEntityException("Customer not found: " + saleRequestDto.getCustomerId()));

        Sale sale = saleMapper.toEntity(saleRequestDto);
        sale.setSaleData(new Date());

        Sale savedSale = saleRepository.save(sale);

        processSaleItems(saleRequestDto.getItems(), savedSale);

        return saleMapper.toDto(savedSale);
    }

    @Override
    public SaleResponseDto getSaleById(Long saleId) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Sale not found" + saleId));

        return saleMapper.toDto(sale);
    }

    @Override
    public List<SaleResponseDto> getAllSales() {
        return saleRepository.findAll()
                .stream()
                .map(saleMapper::toDto)
                .toList();
    }

    private void processSaleItems(List<SaleItemDto> itemsDto, Sale savedSale) {
        List<SaleItem> saleItems = new ArrayList<>();

        for (SaleItemDto itemDto : itemsDto) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + itemDto.getProductId()));

            SaleItem item = saleItemMapper.toEntity(itemDto);
            item.setProduct(product);
            item.setSale(savedSale);

            double quantity = item.getQuantity();
            double unitPrice = Double.parseDouble(item.getUnitPrice()); // Cuidado com parse!
            item.setSubtotal(String.valueOf(quantity * unitPrice));

            productHasSaleRepository.save(item);
            saleItems.add(item);
        }

        savedSale.setSaleItems(saleItems);
    }

}