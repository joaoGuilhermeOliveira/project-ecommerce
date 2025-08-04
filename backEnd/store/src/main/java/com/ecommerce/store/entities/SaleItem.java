package com.ecommerce.store.entities;

import com.ecommerce.store.enums.SaleItemId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Entity
@Data
public class SaleItem {
    @EmbeddedId
    private SaleItemId id = new SaleItemId();

    @ManyToOne
    @MapsId("productId")
    private Product product;

    @ManyToOne
    @MapsId("saleId")
    private Sale sale;

    private Integer quantity;
    private String unityPrice;
    private String paymentMethod;
}
