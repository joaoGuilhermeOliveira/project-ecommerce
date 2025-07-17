package com.ecommerce.store.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.store.entities.Address;
import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerByCpf(String cpf) {
        return customerRepository.findByCpf(cpf);
    }

    @Override
    public void updateCustomerByCpf(String cpf, Customer updateCustomer) {
        this.updateCustomer(cpf, updateCustomer);
    }

    private void updateCustomer(String cpf, Customer updateCustomer) {
        Customer customer = getCustomerByCpf(cpf);

        customer.setName(updateCustomer.getName() != null ? updateCustomer.getName() : customer.getName());

        customer.setEmail(updateCustomer.getEmail() != null ? updateCustomer.getEmail() : customer.getEmail());

        customer.setPhone(updateCustomer.getPhone() != null ? updateCustomer.getPhone() : customer.getPhone());

        customer.setAddress(this.updateCustomerAddress(customer, updateCustomer.getAddress()));

        customer.setBirthDate(
                updateCustomer.getBirthDate() != null ? updateCustomer.getBirthDate() : customer.getBirthDate());

        customer.setPassword(
                updateCustomer.getPassword() != null ? updateCustomer.getPassword() : customer.getPassword());

        customerRepository.save(customer);
    }

    private Address updateCustomerAddress(Customer customer, Address updateCustomerAddress) {
        Address address = customer.getAddress();

        if (updateCustomerAddress == null) {
            return address;
        }

        address.setCity(updateCustomerAddress.getCity() != null ? updateCustomerAddress.getCity() : address.getCity());

        address.setDistrict(updateCustomerAddress.getDistrict() != null ? updateCustomerAddress.getDistrict()
                : address.getDistrict());

        address.setState(
                updateCustomerAddress.getState() != null ? updateCustomerAddress.getState() : address.getState());

        address.setStreet(
                updateCustomerAddress.getStreet() != null ? updateCustomerAddress.getStreet() : address.getStreet());

        address.setNumber(
                updateCustomerAddress.getNumber() != null ? updateCustomerAddress.getNumber() : address.getNumber());

        address.setZipCode(
                updateCustomerAddress.getZipCode() != null ? updateCustomerAddress.getZipCode() : address.getZipCode());

        return address;

    }

    @Override
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
}
