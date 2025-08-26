package com.ecommerce.store.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.ecommerce.store.keycloak.KeycloakProperties;
import com.ecommerce.store.web.dtos.responses.KeycloakTokenResponseDto;

import java.util.HashMap;
import java.util.Map;

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


    private String getAdminAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/realms/master/protocol/openid-connect/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", "admin-cli");
        params.add("username", "admin");
        params.add("password", "admin");
        params.add("grant_type", "password");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        return (String) response.getBody().get("access_token");
    }

    public ResponseEntity<String> createUser(String username, String firstName, String lastName, String password, String email) {
        String token = getAdminAccessToken();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/admin/realms/ecommerce/users";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        Map<String, Object> user = new HashMap<>();
        user.put("username", username);
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("email", email);
        user.put("enabled", true);

        Map<String, Object> credentials = new HashMap<>();
        credentials.put("type", "password");
        credentials.put("value", password);
        credentials.put("temporary", false);
        user.put("credentials", new Object[]{credentials});

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(user, headers);
        return restTemplate.postForEntity(url, request, String.class, "ecommerce");
    }
}
