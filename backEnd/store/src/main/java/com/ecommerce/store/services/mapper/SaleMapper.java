package com.ecommerce.store.services.mapper;

import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.entities.Sale;
import com.ecommerce.store.web.dtos.requests.CustomerRequestDto;
import com.ecommerce.store.web.dtos.requests.SaleRequestDto;
import com.ecommerce.store.web.dtos.responses.SaleResponseDto;
import org.springframework.stereotype.Component;

@Component
public class SaleMapper {


    public Sale toEntity(SaleRequestDto dto) {
        Sale sale = new Sale();
        sale.setSaleData(dto.getSaleData());
        sale.setFreightPrice(dto.getFreightPrice());
        sale.setTotalPice(dto.getTotalPrice());
        sale.setSaleValue(dto.getSaleValue());

        if (dto.getCustomer() != null && dto.getCustomer().getId() != null) {
            Customer customer = new Customer();
            customer.setId(dto.getCustomer().getId());
            sale.setCustomer(customer);
        }

        return sale;
    }

    public SaleResponseDto toDto(Sale entity) {
        SaleResponseDto dto = new SaleResponseDto();
        dto.setSaleData(entity.getSaleData());
        dto.setFreightPrice(entity.getFreightPrice());
        dto.setTotalPrice(entity.getTotalPice());
        dto.setSaleValue(entity.getSaleValue());
        dto.setId(entity.getId());
        return dto;
    }
}
