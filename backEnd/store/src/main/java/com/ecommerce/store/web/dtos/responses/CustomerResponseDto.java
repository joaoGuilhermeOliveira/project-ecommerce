package com.ecommerce.store.web.dtos.responses;

import com.ecommerce.store.entities.Address;

import lombok.Data;

@Data
public class CustomerResponseDto {
    private String name;
    private String cpf;
    private String email;
    private Address address;
    private String birthDate;
    private String phone;
}
