package com.ecommerce.store.services.mapper;

import org.springframework.stereotype.Component;

import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.services.dtos.requests.CustomerDto;

@Component
public class CustomerMapper {
    
    public Customer toEntity(CustomerDto customerRequestDto) {
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

    public CustomerDto toDto (Customer customer) {
        CustomerDto customerRequestDto = new CustomerDto();
        customerRequestDto.setCustomerId(customer.getId());
        customerRequestDto.setName(customer.getName());
        customerRequestDto.setCpf(customer.getCpf());
        customerRequestDto.setEmail(customer.getEmail());
        customerRequestDto.setAddress(customer.getAddress());
        customerRequestDto.setBirthDate(customer.getBirthDate());
        customerRequestDto.setPhone(customer.getPhone());

        return customerRequestDto;
    }
}
