
package com.ecommerce.store.web.controllers;

import com.ecommerce.store.web.dtos.requests.ResetPasswordDto;
import com.ecommerce.store.web.dtos.responses.KeycloakTokenResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.store.services.AuthService;
import com.ecommerce.store.web.dtos.requests.LoginRequestDto;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) {
        KeycloakTokenResponseDto authenticated = authService.authenticate(loginRequestDto);
        if (authenticated != null) {
            return ResponseEntity.ok("Login realizado com sucesso!");
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas.");
        }
    }

    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        boolean authenticated = authService.resetPassword(resetPasswordDto);
        if (authenticated) {
            return ResponseEntity.ok("Troca de senha realizada com sucesso!");
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas.");
        }
    }

}
