package com.ecommerce.store.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.store.entities.Address;
import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.enums.StatusEnum;
import com.ecommerce.store.repositories.CustomerRepository;
import com.ecommerce.store.services.mapper.CustomerMapper;
import com.ecommerce.store.web.dtos.responses.CustomerResponseDto;
import com.ecommerce.store.web.dtos.requests.CustomerRequestDto;

import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public void createCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = customerMapper.toEntity(customerRequestDto);
        customer.setStatus(StatusEnum.ACTIVE);
        customerRepository.save(customer);
    }

    @Override
    public CustomerResponseDto getCustomerByCpf(String cpf) {
        Customer customer = customerRepository.findByCpf(cpf);
        return customerMapper.toDto(customer);
    }

    @Override
    public void updateCustomerByCpf(String cpf, CustomerRequestDto updateCustomer) {
        this.updateCustomer(cpf, updateCustomer);
    }

    private void updateCustomer(String cpf, CustomerRequestDto updateCustomer) {

        Customer customer = customerRepository.findByCpf(cpf);

        customer.setName(updateCustomer.getName() != null ? updateCustomer.getName() : customer.getName());

        customer.setEmail(updateCustomer.getEmail() != null ? updateCustomer.getEmail() : customer.getEmail());

        customer.setPhone(updateCustomer.getPhone() != null ? updateCustomer.getPhone() : customer.getPhone());

        customer.setAddress(this.updateCustomerAddress(customer, updateCustomer.getAddress()));

        customer.setBirthDate(
                updateCustomer.getBirthDate() != null ? updateCustomer.getBirthDate() : customer.getBirthDate());

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
}
