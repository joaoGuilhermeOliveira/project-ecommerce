package com.ecommerce.store.services.mapper;

import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.entities.Sale;
import com.ecommerce.store.web.dtos.request.CustomerDto;
import com.ecommerce.store.web.dtos.request.SaleDto;
import org.springframework.stereotype.Component;

@Component
public class SaleMapper {


    public Sale toEntity(SaleDto dto) {
        Sale sale = new Sale();
        sale.setSaleData(dto.getSaleData());
        sale.setFreightPrice(dto.getFreightPrice());
        sale.setTotalPice(dto.getTotalPice());
        sale.setSaleValue(dto.getSaleValue());

        if (dto.getCustomer() != null && dto.getCustomer().getCustomerId() != null) {
            Customer customer = new Customer();
            customer.setCustomerId(dto.getCustomer().getCustomerId());
            sale.setCustomer(customer);
        }

        return sale;
    }

    public SaleDto toDto(Sale entity) {
        SaleDto dto = new SaleDto();
        dto.setSaleData(entity.getSaleData());
        dto.setFreightPrice(entity.getFreightPrice());
        dto.setTotalPice(entity.getTotalPice());
        dto.setSaleValue(entity.getSaleValue());

        if (entity.getCustomer() != null) {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setCustomerId(entity.getCustomer().getCustomerId());
            customerDto.setName(entity.getCustomer().getName());
            customerDto.setCpf(entity.getCustomer().getCpf());
            customerDto.setEmail(entity.getCustomer().getEmail());
            customerDto.setAddress(entity.getCustomer().getAddress());
            customerDto.setBirthDate(entity.getCustomer().getBirthDate());
            customerDto.setPhone(entity.getCustomer().getPhone());
            dto.setCustomer(customerDto);
        }

        return dto;
    }
}
