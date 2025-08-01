package com.ecommerce.store.repositories;

import org.springframework.stereotype.Repository;
import com.ecommerce.store.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>{
    boolean existsByNameIgnoreCase(String name);
}
