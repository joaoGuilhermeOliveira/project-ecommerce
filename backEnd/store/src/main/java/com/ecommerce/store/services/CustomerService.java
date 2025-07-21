package com.ecommerce.store.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public void createCustomer(CustomerRequestDto customerRequestDto) {
        logger.info("Creating customer with CPF: {}", customerRequestDto.getCpf());
        try {
            Customer customer = customerMapper.toEntity(customerRequestDto);
            customer.setStatus(StatusEnum.ACTIVE);
            customerRepository.save(customer);
            logger.info("Customer created successfully: {}", customer.getCpf());
        } catch (Exception e) {
            logger.error("Error creating customer with CPF: {}", customerRequestDto.getCpf(), e);
            throw e;
        }
    }

    public CustomerResponseDto getCustomerByCpf(String cpf) {
        logger.info("Fetching customer by CPF: {}", cpf);
        try {
            Customer customer = customerRepository.findByCpf(cpf);
            if (customer == null) {
                logger.error("Customer not found for CPF: {}", cpf);
                throw new RuntimeException("Cliente não encontrado para o CPF: " + cpf);
            }
            CustomerResponseDto response = customerMapper.toDto(customer);
            logger.info("Customer found for CPF: {}", cpf);
            return response;
        } catch (Exception e) {
            logger.error("Error fetching customer by CPF: {}", cpf, e);
            throw e;
        }
    }

    public void updateCustomerByCpf(String cpf, CustomerRequestDto updateCustomer) {
        logger.info("Updating customer with CPF: {}", cpf);
        try {
            this.updateCustomer(cpf, updateCustomer);
            logger.info("Customer updated successfully: {}", cpf);
        } catch (Exception e) {
            logger.error("Error updating customer with CPF: {}", cpf, e);
            throw e;
        }
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
