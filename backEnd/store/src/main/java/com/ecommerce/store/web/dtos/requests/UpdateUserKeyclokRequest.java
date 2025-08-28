package com.ecommerce.store.web.dtos.requests;

import lombok.Data;

@Data
public class UpdateUserKeyclokRequest {
    private String firstName;
    private String lastName;
    private String email;

}
