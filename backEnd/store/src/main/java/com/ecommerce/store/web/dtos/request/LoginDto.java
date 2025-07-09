package com.ecommerce.store.web.dtos.request;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
