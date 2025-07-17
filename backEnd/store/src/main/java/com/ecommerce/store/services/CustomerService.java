package com.ecommerce.store.services;

import com.ecommerce.store.entities.Customer;

import java.util.List;

public interface CustomerService {

    Customer createCustomer(Customer customer);

    Customer getCustomerByCpf(String cpf);

    void updateCustomerByCpf(String cpf, Customer updateCustomer);

    List<Customer> getAllCustomers();
}
