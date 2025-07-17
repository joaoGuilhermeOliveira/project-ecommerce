package com.ecommerce.store.services;

public interface AuthService {
    boolean authenticate(String email, String password);

}
