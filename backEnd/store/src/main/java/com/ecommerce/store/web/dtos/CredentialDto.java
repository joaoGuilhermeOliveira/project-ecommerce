package com.ecommerce.store.web.dtos;

import lombok.Data;

@Data
public class CredentialDto {
    private String type;
    private String value;
    private Boolean temporary = false;
}
