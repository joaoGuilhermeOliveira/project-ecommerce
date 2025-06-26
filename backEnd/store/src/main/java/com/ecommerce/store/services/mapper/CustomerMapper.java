package com.ecommerce.store.services.mapper;

import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.web.dtos.request.CustomerRequestDto;

public class CustomerMapper {
    
    public Customer toEntity(CustomerRequestDto customerRequestDto) {
        Customer customer = new Customer();
        customer.setName(customerRequestDto.getName());
        customer.setCpf(customerRequestDto.getCpf());
        customer.setEmail(customerRequestDto.getEmail());
        customer.setAddress(customerRequestDto.getAddress());
        customer.setBirthDate(customerRequestDto.getBirthDate());
        customer.setPhone(customerRequestDto.getPhone());

        return customer;
    }

}
