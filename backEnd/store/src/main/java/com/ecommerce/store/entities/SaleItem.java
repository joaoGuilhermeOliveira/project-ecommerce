package com.ecommerce.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "sale_item")
@NoArgsConstructor
@AllArgsConstructor
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_item_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sale_sale_id", nullable = false)
    private Sale sale;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false, length = 10)
    private String unitPrice;

    @Column(name = "subtotal", nullable = false, length = 10)
    private String subtotal;

}
