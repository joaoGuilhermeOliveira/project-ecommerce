package com.ecommerce.store.web.dtos.requests;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
