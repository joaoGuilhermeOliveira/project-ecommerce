package com.ecommerce.store.web.dtos.requests;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private String email;
    private String password;
    private String confirmPassword;
}
