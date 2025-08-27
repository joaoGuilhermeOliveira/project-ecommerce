package com.ecommerce.store.services;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import com.ecommerce.store.keycloak.KeycloakProperties;
import com.ecommerce.store.web.dtos.CredentialsDto;
import com.ecommerce.store.web.dtos.requests.KeycloakCreateUserRequestDto;
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

    public ResponseEntity<String> createUser(String username, String firstName, String lastName, String password,
            String email) {
        String token = this.getAdminAccessToken();

        KeycloakCreateUserRequestDto createKeycloakUserRequest = buildCreateUserRequest(username, firstName, lastName,
                password, email);

        return webClient.post()
            .uri(properties.getCreateUserUrl())
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(createKeycloakUserRequest)
            .exchangeToMono(response ->
                    response.bodyToMono(String.class)
                            .defaultIfEmpty("")
                            .map(body -> ResponseEntity.status(response.statusCode()).body(body))
            )
            .block();
    }

    private String getAdminAccessToken() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", "admin-cli");
        params.add("username", properties.getAdminUsername());
        params.add("password", properties.getAdminPassword());
        params.add("grant_type", "password");

        KeycloakTokenResponseDto response = webClient.post()
                .uri(properties.getAdminTokenUrl())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(params)
                .retrieve()
                .bodyToMono(KeycloakTokenResponseDto.class)
                .block();
        return response.getAccessToken();
    }

    private KeycloakCreateUserRequestDto buildCreateUserRequest(String username, String firstName, String lastName,
            String password,
            String email) {
        KeycloakCreateUserRequestDto request = new KeycloakCreateUserRequestDto();
        request.setUsername(username);
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setEmail(email);
        request.setEnabled(true);
        request.setEmailVerified(true);

        CredentialsDto credentials = new CredentialsDto();
        credentials.setType("password");
        credentials.setValue(password);
        credentials.setTemporary(false);
        request.setCredentials(new CredentialsDto[] { credentials });

        return request;
    }
}
