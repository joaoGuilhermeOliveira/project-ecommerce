package com.ecommerce.store.entities;

import java.sql.Blob;
import java.util.List;

import com.ecommerce.store.enums.ProductStatusEnum;

import jakarta.persistence.*;
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
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "cost_price", nullable = false, length = 10)
    private String costPrice;

    @Column(name = "sell_price", nullable = false, length = 10)
    private String sellPrice;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "image")
    private Blob image;

    @Column(name = "gtin", nullable = false, unique = true, length = 14)
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
    @Enumerated(EnumType.STRING)
    private ProductStatusEnum status;

    @OneToMany(mappedBy = "product")
    private List<SaleItem> productSales;
}
