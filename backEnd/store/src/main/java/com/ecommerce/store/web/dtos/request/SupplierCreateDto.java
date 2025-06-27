package com.ecommerce.store.web.dtos.request;

import com.ecommerce.store.entities.Address;
import lombok.Data;

@Data
public class SupplierCreateRequestDto {

    private String cnpj;
    private String name;
    private String phone_number;
    private String email;
    private Address address;
}
