package com.ecommerce.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "supplier")
public class Supplier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplierId",nullable = false)
    private Long id;
    @Column(name = "cnpj", nullable = false, length = 14)
    private String cnpj;
    @Column(name = "name", nullable = false, length = 200)
    private String name;
    @Column(name = "phone_number", nullable = false, length = 18)
    private String phone_number;
    @Column(name = "email", nullable = false, length = 200)
    private String email;
    @Embedded
    private Address address;
}