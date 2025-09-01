package com.ecommerce.store.web.dtos.responses;

import java.util.List;


import com.ecommerce.store.web.dtos.CredentialDto;

import lombok.Data;

@Data
public class UserKeycloakResponseDto {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean emailVerified;
    private Boolean enabled;
    private Boolean totp;
    private List<String> requiredActions;
    private List<CredentialDto> credentials;
}
