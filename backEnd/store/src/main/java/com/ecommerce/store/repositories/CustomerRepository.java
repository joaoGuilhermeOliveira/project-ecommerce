package com.ecommerce.store.repositories;

import com.ecommerce.store.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Customer findByCpf(String cpf);
    public Customer findByEmail(String email);
    boolean existsByCpf(String cpf);
} 