package com.ecommerce.store.services;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import com.ecommerce.store.keycloak.KeycloakProperties;
import com.ecommerce.store.web.dtos.responses.KeycloakTokenResponseDto;

@Service
public class KeycloakService {

    private final WebClient webClient;
    private final KeycloakProperties properties;

    public KeycloakService(KeycloakProperties properties) {
        this.webClient = WebClient.create();
        this.properties = properties;
    }

    public KeycloakTokenResponseDto getToken(String username, String password) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        System.out.println(properties.getClientId() + " " + properties.getClientSecret() + " " + properties.getTokenUrl());
        formData.add("grant_type", "password");
        formData.add("client_id", properties.getClientId());
        formData.add("client_secret", properties.getClientSecret());
        formData.add("username", username);
        formData.add("password", password);

        return webClient.post()
                .uri(properties.getTokenUrl())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(formData)
                .retrieve()
                .bodyToMono(KeycloakTokenResponseDto.class)
                .block();
    }
}
