
package com.ecommerce.store.services;


import com.ecommerce.store.web.dtos.requests.LoginRequestDto;
import com.ecommerce.store.web.dtos.requests.ResetPasswordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.repositories.CustomerRepository;

import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    private CustomerRepository customerRepository;

    public boolean authenticate(LoginRequestDto loginRequestDto) {
        Customer customer = customerRepository.findByEmail(loginRequestDto.getEmail());
        if(Objects.equals(loginRequestDto.getPassword(), loginRequestDto.getConfirmPassword())){
            return customer != null && customer.getPassword().equals(loginRequestDto.getPassword());
        }
        return false;
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
}
