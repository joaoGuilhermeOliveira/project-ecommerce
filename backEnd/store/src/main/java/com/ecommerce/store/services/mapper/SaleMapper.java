package com.ecommerce.store.services.mapper;

import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.entities.Sale;
import com.ecommerce.store.web.dtos.SaleItemDto;
import com.ecommerce.store.web.dtos.requests.SaleRequestDto;
import com.ecommerce.store.web.dtos.responses.ProductHasSaleResponseDto;
import com.ecommerce.store.web.dtos.responses.SaleResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SaleMapper {

    @Autowired
    private SaleItemMapper saleItemMapper;

    public Sale toEntity(SaleRequestDto dto) {
        Sale sale = new Sale();
        sale.setFreightPrice(dto.getFreightPrice());
        sale.setTotalPrice(dto.getTotalPrice());
        sale.setPaymentMethod(dto.getPaymentMethod());
        sale.setSaleValue(dto.getSaleValue());

        if (dto.getCustomerId() != null) {
            Customer customer = new Customer();
            customer.setId(dto.getCustomerId());
            sale.setCustomer(customer);
        }

        return sale;
    }

    public SaleResponseDto toDto(Sale entity) {
        SaleResponseDto dto = new SaleResponseDto();
        dto.setSaleData(entity.getSaleData());
        dto.setFreightPrice(entity.getFreightPrice());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setSaleValue(entity.getSaleValue());
        dto.setPaymentMethod(entity.getPaymentMethod());
        dto.setId(entity.getId());

        if (entity.getSaleItems() != null) {
            List<SaleItemDto> items = entity.getSaleItems()
                    .stream()
                    .map(saleItemMapper::toDto)
                    .toList();
            dto.setSaleItems(items);
        }

        return dto;
    }
}