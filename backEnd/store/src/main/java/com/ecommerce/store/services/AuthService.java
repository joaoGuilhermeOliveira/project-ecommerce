
package com.ecommerce.store.services;

import com.ecommerce.store.web.dtos.requests.LoginRequestDto;
import com.ecommerce.store.web.dtos.requests.ResetPasswordDto;
import com.ecommerce.store.web.dtos.responses.KeycloakTokenResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.repositories.CustomerRepository;
import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private KeycloakService keycloakService;

    public KeycloakTokenResponseDto authenticate(LoginRequestDto loginRequestDto) {
        Customer customer = customerRepository.findByEmail(loginRequestDto.getEmail());

        if (customer == null || customer.getStatus().name().equals("INACTIVE") && !validateCredentials(customer.getEmail(), customer.getPassword())) {
            return null;
        }

        KeycloakTokenResponseDto tokenResponse = keycloakService.getToken(
            loginRequestDto.getEmail(),
            loginRequestDto.getPassword()
        );

        return tokenResponse;
    }

    public boolean resetPassword(ResetPasswordDto resetPasswordDto) {
        Customer customer = customerRepository.findByEmail(resetPasswordDto.getEmail());

        if (customer != null) {
            if (Objects.equals(resetPasswordDto.getPassword(), resetPasswordDto.getConfirmPassword())) {
                customer.setPassword(resetPasswordDto.getPassword());
                customerRepository.save(customer);
                return true;
            }
        }
        return false;
    }

    private boolean validateCredentials(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        return customer != null && customer.getPassword().equals(password);
}
}
