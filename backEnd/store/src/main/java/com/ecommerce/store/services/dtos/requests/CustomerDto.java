package com.ecommerce.store.services.dtos.requests;

import com.ecommerce.store.entities.Address;

import lombok.Data;

@Data
public class CustomerDto {
    private Long customerId;
    private String name;
    private String cpf;
    private String email;
    private Address address;
    private String birthDate;
    private String phone;
    private String password;
}
