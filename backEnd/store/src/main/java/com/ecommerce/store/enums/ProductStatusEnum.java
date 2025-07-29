package com.ecommerce.store.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductStatusEnum {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private final String status;
}
