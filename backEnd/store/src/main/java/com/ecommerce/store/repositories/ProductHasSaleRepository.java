package com.ecommerce.store.repositories;

import com.ecommerce.store.entities.SaleItem;
import com.ecommerce.store.enums.SaleItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductHasSaleRepository extends JpaRepository<SaleItem, SaleItemId> {

}
