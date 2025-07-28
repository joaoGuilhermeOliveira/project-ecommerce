package com.ecommerce.store.web.dtos.responses;

import com.ecommerce.store.entities.Address;
import com.ecommerce.store.enums.StatusEnum;

import lombok.Data;

@Data
public class SupplierResponseDto {
    private String cnpj;
    private String name;
    private String phone_number;
    private String email;
    private Address address;
    private StatusEnum status;
}
