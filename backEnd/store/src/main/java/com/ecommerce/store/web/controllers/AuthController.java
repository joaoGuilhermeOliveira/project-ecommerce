
package com.ecommerce.store.web.controllers;

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
        boolean authenticated = authService.authenticate(loginRequestDto);
        if (authenticated) {
            return ResponseEntity.ok("Login realizado com sucesso!");
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas.");
        }
    }
}
