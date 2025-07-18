package com.ecommerce.store.web.dtos.requests;

import com.ecommerce.store.entities.Address;
import lombok.Data;

@Data
public class SupplierRequestDto {
    private String cnpj;
    private String name;
    private String phone_number;
    private String email;
    private Address address;
}
