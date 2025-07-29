
package com.ecommerce.store.services;


import com.ecommerce.store.web.dtos.requests.LoginRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.repositories.CustomerRepository;

@Service
public class AuthService {

    @Autowired
    private CustomerRepository customerRepository;

    public boolean authenticate(LoginRequestDto loginRequestDto) {
        Customer customer = customerRepository.findByEmail(loginRequestDto.getEmail());
        return customer != null && customer.getPassword().equals(loginRequestDto.getPassword());
    }
}
