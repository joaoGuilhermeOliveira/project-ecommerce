package com.ecommerce.store.services;

import com.ecommerce.store.web.dtos.requests.CustomerRequestDto;
import com.ecommerce.store.web.dtos.requests.CustomerUpdateStatusRequestDto;
import com.ecommerce.store.web.dtos.responses.CustomerResponseDto;

public interface CustomerService {
    void createCustomer(CustomerRequestDto customerRequestDto);
    CustomerResponseDto getCustomerByCpf(String cpf);
    void updateCustomerByCpf(String cpf, CustomerRequestDto updateCustomer);
    void updateStatusByCpf(String cpf, CustomerUpdateStatusRequestDto updateStatus);
}
