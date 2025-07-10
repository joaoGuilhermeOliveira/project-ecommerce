package com.ecommerce.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "customer_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "cpf", nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Embedded
    private Address address;

    @Column(name = "birth_date", nullable = false, length = 10)
    private String birthDate;

    @Column(name = "phone", nullable = false, length = 18)
    private String phone;

    @Column(name = "password", nullable = false, length = 255)
    private String password;
}
