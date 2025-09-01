package com.ecommerce.store.services;

import com.ecommerce.store.web.dtos.requests.UpdateUserKeyclokRequest;
import org.springframework.core.ParameterizedTypeReference;
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
import com.ecommerce.store.web.dtos.responses.UserKeycloakResponseDto;

import java.util.List;
import java.util.Objects;

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
                .exchangeToMono(response -> response.bodyToMono(String.class)
                        .defaultIfEmpty("")
                        .map(body -> ResponseEntity.status(response.statusCode()).body(body)))
                .block();
    }

    public void updateKeycloakUser(String email, UpdateUserKeyclokRequest request) {
        String token = getAdminAccessToken();

        UserKeycloakResponseDto user = this.getKeycloakUser(email);

        user.setFirstName(
                Objects.requireNonNullElse(request.getFirstName(), user.getFirstName()));
        user.setLastName(Objects.requireNonNullElse(request.getLastName(), user.getLastName()));
        user.setEmail(Objects.requireNonNullElse(request.getEmail(), user.getEmail()));
        if (request.getPassword() != null) {
            user.setCredentials(List.of(
                    new CredentialsDto("password", request.getPassword(), false)));
        }
        webClient.put()
                .uri(properties.getPutUserUrl() + "/" + user.getId())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .retrieve()
                .toBodilessEntity()
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

    private UserKeycloakResponseDto getKeycloakUser(String email) {
        String token = getAdminAccessToken();

        List<UserKeycloakResponseDto> users = webClient.get()
                .uri(properties.getUsersUrl() + "?email=" + email)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<UserKeycloakResponseDto>>() {
                })
                .block();

        if (users == null || users.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado no Keycloak");
        }

        return users.get(0);
    }

}
