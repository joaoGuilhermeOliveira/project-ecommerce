package com.ecommerce.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Address {

    @Column(name = "address_street", nullable = false, length = 45)
    private String street;

    @Column(name = "address_number", nullable = false, length = 5)
    private String number;

    @Column(name = "address_district", nullable = false, length = 25)
    private String district;

    @Column(name = "city", nullable = false, length = 25)
    private String city;

    @Column(name = "state", nullable = false, length = 2)
    private String state;

    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipCode;
}
