package com.ecommerce.store.entities;

import java.sql.Blob;

import com.ecommerce.store.enums.ProductStatusEnum;

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
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "cost_price", nullable = false, length = 10)
    private String costPrice;

    @Column(name = "sell_price", nullable = false, length = 10)
    private String sellPrice;

    @Column(name = "description", nullable = true, length = 500)
    private String description;

    @Column(name = "image", nullable = true)
    private Blob image;

    @Column(name = "gtin", nullable = false, length = 14)
    private String gtin;

    @JoinColumn(name = "category_category_id", nullable = false)
    @ManyToOne
    private Category category;

    @JoinColumn(name = "supplier_supplier_id", nullable = false)
    @ManyToOne
    private Supplier supplier;

    @JoinColumn(name = "brand_brand_id", nullable = false)
    @ManyToOne
    private Brand brand;

    @Column(name = "status", nullable = false)
    private ProductStatusEnum status;
}
