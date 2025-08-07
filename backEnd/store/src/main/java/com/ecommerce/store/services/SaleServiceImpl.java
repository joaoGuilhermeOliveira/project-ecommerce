package com.ecommerce.store.services;

import com.ecommerce.store.entities.Customer;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class SaleServiceImpl implements SaleService{

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final SaleMapper saleMapper;
    private final ProductRepository productRepository;
    private final ProductHasSaleRepository productHasSaleRepository;
    private final SaleItemMapper saleItemMapper;

    public SaleServiceImpl(
            SaleRepository saleRepository,
            CustomerRepository customerRepository,
            SaleMapper saleMapper,
            ProductRepository productRepository,
            ProductHasSaleRepository productHasSaleRepository,
            SaleItemMapper saleItemMapper) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.saleMapper = saleMapper;
        this.productRepository = productRepository;
        this.productHasSaleRepository = productHasSaleRepository;
        this.saleItemMapper = saleItemMapper;
    }
    @Override
    @Transactional
    public SaleResponseDto createSale(SaleRequestDto saleRequestDto) {
        log.info("Creating sale for customer ID: {}", saleRequestDto.getCustomerId());
        Customer customer = customerRepository.findById(saleRequestDto.getCustomerId())
                .orElseThrow(() -> new InvalidEntityException("Customer not found: " + saleRequestDto.getCustomerId()));

        Sale sale = saleMapper.toEntity(saleRequestDto);
        sale.setSaleData(new Date());

        Sale savedSale = saleRepository.save(sale);

        processSaleItems(saleRequestDto.getItems(), savedSale);
        log.info("Sale created successfully with ID: {}", savedSale.getId());

        return saleMapper.toDto(savedSale);
    }

    @Override
    public SaleResponseDto getSaleById(Long saleId) {
        log.info("Fetching sale with ID: {}", saleId);

        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Sale not found" + saleId));

        return saleMapper.toDto(sale);
    }

    @Override
    public List<SaleResponseDto> getAllSales() {
        log.info("Fetching all sales");

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