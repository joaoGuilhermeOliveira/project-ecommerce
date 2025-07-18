package com.ecommerce.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.store.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
