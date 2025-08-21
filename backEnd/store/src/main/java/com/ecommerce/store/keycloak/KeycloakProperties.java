package com.ecommerce.store.keycloak;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "keycloak")
public class KeycloakProperties {
    private String tokenUrl;
    private String clientId;
    private String clientSecret;
}
