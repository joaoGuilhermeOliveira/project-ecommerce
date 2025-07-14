package com.ecommerce.store.services.mapper;

import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.entities.Sale;
import com.ecommerce.store.web.dtos.requests.CustomerRequestDto;
import com.ecommerce.store.web.dtos.requests.SaleRequestDto;
import org.springframework.stereotype.Component;

@Component
public class SaleMapper {


    public Sale toEntity(SaleRequestDto dto) {
        Sale sale = new Sale();
        sale.setSaleData(dto.getSaleData());
        sale.setFreightPrice(dto.getFreightPrice());
        sale.setTotalPice(dto.getTotalPice());
        sale.setSaleValue(dto.getSaleValue());

        if (dto.getCustomer() != null && dto.getCustomer().getId() != null) {
            Customer customer = new Customer();
            customer.setId(dto.getCustomer().getId());
            sale.setCustomer(customer);
        }

        return sale;
    }

    public SaleRequestDto toDto(Sale entity) {
        SaleRequestDto dto = new SaleRequestDto();
        dto.setSaleData(entity.getSaleData());
        dto.setFreightPrice(entity.getFreightPrice());
        dto.setTotalPice(entity.getTotalPice());
        dto.setSaleValue(entity.getSaleValue());
        dto.setId(entity.getId());

        if (entity.getCustomer() != null) {
            CustomerRequestDto customerRequestDto = new CustomerRequestDto();
            customerRequestDto.setId(entity.getCustomer().getId());
            customerRequestDto.setName(entity.getCustomer().getName());
            customerRequestDto.setCpf(entity.getCustomer().getCpf());
            customerRequestDto.setEmail(entity.getCustomer().getEmail());
            customerRequestDto.setAddress(entity.getCustomer().getAddress());
            customerRequestDto.setBirthDate(entity.getCustomer().getBirthDate());
            customerRequestDto.setPhone(entity.getCustomer().getPhone());
            dto.setCustomer(customerRequestDto);
        }

        return dto;
    }
}
