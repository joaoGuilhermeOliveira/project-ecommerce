package com.ecommerce.store.services.mapper;

import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.web.dtos.request.CustomerDto;
import org.springframework.stereotype.Component;

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

        return customer;
    }

    public CustomerDto toDto (Customer customer) {
        CustomerDto customerRequestDto = new CustomerDto();
        customerRequestDto.setCustomerId(customer.getCustomerId());
        customerRequestDto.setName(customer.getName());
        customerRequestDto.setCpf(customer.getCpf());
        customerRequestDto.setEmail(customer.getEmail());
        customerRequestDto.setAddress(customer.getAddress());
        customerRequestDto.setBirthDate(customer.getBirthDate());
        customerRequestDto.setPhone(customer.getPhone());

        return customerRequestDto;
    }
}
