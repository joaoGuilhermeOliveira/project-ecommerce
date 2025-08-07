package com.ecommerce.store.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import com.ecommerce.store.enums.PaymentMethodEnum;

@Entity
@Table(name = "Sale")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "sale_id", nullable = false)
    private Long id;

    @Column(name= "sale_date")
    private Date saleData;

    @Column(name= "sale_value")
    private String saleValue;

    @Column(name= "freight_price")
    private String freightPrice;

    @Column(name= "total_price")
    private String totalPrice;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethodEnum paymentMethod;

    @ManyToOne
    @JoinColumn(name = "customer_customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItem> saleItems;
}