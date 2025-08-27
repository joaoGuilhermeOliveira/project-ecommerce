package com.ecommerce.store.web.dtos.requests;

import com.ecommerce.store.web.dtos.CredentialsDto;

import lombok.Data;

@Data
public class KeycloakCreateUserRequestDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private boolean enabled = true;
    private boolean emailVerified = true;
    private CredentialsDto credentials[];
}
