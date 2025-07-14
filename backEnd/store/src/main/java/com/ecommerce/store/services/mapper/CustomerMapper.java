package com.ecommerce.store.services.mapper;

import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.web.dtos.requests.CustomerRequestDto;
import com.ecommerce.store.web.dtos.responses.CustomerResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    
    public Customer toEntity(CustomerRequestDto customerRequestDto) {
        Customer customer = new Customer();
        customer.setName(customerRequestDto.getName());
        customer.setCpf(customerRequestDto.getCpf());
        customer.setEmail(customerRequestDto.getEmail());
        customer.setAddress(customerRequestDto.getAddress());
        customer.setBirthDate(customerRequestDto.getBirthDate());
        customer.setPhone(customerRequestDto.getPhone());
        customer.setPassword(customerRequestDto.getPassword());
        return customer;
    }

    public CustomerResponseDto toDto (Customer customer) {
        CustomerResponseDto dto = new CustomerResponseDto();
        dto.setCpf(customer.getCpf());
        dto.setName(customer.getName());
        dto.setCpf(customer.getCpf());
        dto.setEmail(customer.getEmail());
        return dto;
    }
}
