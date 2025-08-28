package com.ecommerce.store.services;

import com.ecommerce.store.web.dtos.requests.UpdateStatusRequestDto;
import com.ecommerce.store.web.dtos.requests.UpdateUserKeyclokRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.store.entities.Address;
import com.ecommerce.store.entities.Customer;
import com.ecommerce.store.enums.StatusEnum;
import com.ecommerce.store.exceptions.ConflictException;
import com.ecommerce.store.exceptions.InvalidEntityException;
import com.ecommerce.store.exceptions.NotFoundException;
import com.ecommerce.store.repositories.CustomerRepository;
import com.ecommerce.store.services.mapper.CustomerMapper;
import com.ecommerce.store.web.dtos.responses.CustomerResponseDto;
import com.ecommerce.store.web.dtos.requests.CustomerRequestDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final KeycloakService keycloakService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper,
            KeycloakService keycloakService) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.keycloakService = keycloakService;
    }

    @Override
    public void createCustomer(CustomerRequestDto customerRequestDto) {
        log.info("Creating customer with CPF: {}", customerRequestDto.getCpf());

        if (customerRepository.existsByCpf(customerRequestDto.getCpf())) {
            log.warn("Customer with CPF {} already exists.", customerRequestDto.getCpf());
            throw new ConflictException("Customer with CPF " + customerRequestDto.getCpf() + " already exists.");
        }
        if(customerRepository.existsByEmail(customerRequestDto.getEmail())){
            log.warn("Customer with email {} already exists.", customerRequestDto.getEmail());
            throw new ConflictException("Customer with email " + customerRequestDto.getEmail() + " already exists.");
        }
        else {
            ResponseEntity<String> keycloakResponse = keycloakService.createUser(
                customerRequestDto.getCpf(),
                customerRequestDto.getName(),
                customerRequestDto.getLastName(),
                customerRequestDto.getPassword(),
                customerRequestDto.getEmail());

        if (keycloakResponse.getStatusCode() == HttpStatus.CREATED) {
            Customer customer = customerMapper.toEntity(customerRequestDto);
            customer.setStatus(StatusEnum.ACTIVE);
            customerRepository.save(customer);
            log.info("Customer created successfully: {}", customer.getCpf());

        } else {
            throw new RuntimeException("Erro ao criar usuário no Keycloak: " + keycloakResponse.getBody());
        }
        }
    }

    @Override
    public CustomerResponseDto getCustomerByCpf(String cpf) {
        log.info("Fetching customer with CPF: {}", cpf);

        Customer customer = customerRepository.findByCpf(cpf);
        if (customer == null) {
            log.warn("Customer not found with CPF: {}", cpf);
            throw new NotFoundException("Customer not found with CPF: " + cpf);
        }
        return customerMapper.toDto(customer);
    }

    @Override
    public void updateCustomerByCpf(String cpf, CustomerRequestDto updateCustomerDto) {
        log.info("Updating customer data with CPF: {}", cpf);

        Customer customer = customerRepository.findByCpf(cpf);
        if (customer == null) {
            log.warn("Customer with CPF {} not found for update.", cpf);
            throw new NotFoundException("Customer with CPF " + cpf + " not found.");
        }
        updateCustomer(customer, updateCustomerDto);
        log.info("Customer updated successfully: {}", cpf);
    }

    @Override
    public void updateStatusByCpf(String cpf, UpdateStatusRequestDto updateStatus) {
        log.info("Updating status for customer with CPF: {}", cpf);

        Customer customer = customerRepository.findByCpf(cpf);

        if (customer == null) {
            log.warn("Customer with CPF {} not found for status update.", cpf);
            throw new NotFoundException("Customer with CPF " + cpf + " not found.");
        }

        if (updateStatus.getStatus() == null || updateStatus.getStatus().isBlank()) {
            log.warn("Invalid status received for customer with CPF: {}", cpf);
            throw new InvalidEntityException("Status must be provided.");
        }

        StatusEnum newStatus;
        try {
            newStatus = StatusEnum.valueOf(updateStatus.getStatus().toUpperCase());
        } catch (IllegalArgumentException e) {
            log.error("Invalid status provided: {}", updateStatus.getStatus());
            throw new InvalidEntityException("Invalid status: " + updateStatus.getStatus());
        }

        customer.setStatus(newStatus);
        customerRepository.save(customer);
        log.info("Customer status for {} updated to {}", cpf, newStatus);
    }

    private void updateCustomer(Customer customer, CustomerRequestDto updateCustomer) {
        customer.setName(updateCustomer.getName() != null ? updateCustomer.getName() : customer.getName());
        customer.setEmail(updateCustomer.getEmail() != null ? updateCustomer.getEmail() : customer.getEmail());
        customer.setPhone(updateCustomer.getPhone() != null ? updateCustomer.getPhone() : customer.getPhone());
        customer.setAddress(this.updateCustomerAddress(customer, updateCustomer.getAddress()));
        customer.setBirthDate(
                updateCustomer.getBirthDate() != null ? updateCustomer.getBirthDate() : customer.getBirthDate());

        customerRepository.save(customer);
        try {
            String keycloakId = keycloakService.getKeycloakUserId(updateCustomer.getEmail());

            UpdateUserKeyclokRequest kcRequest = new UpdateUserKeyclokRequest();
            kcRequest.setFirstName(updateCustomer.getName());
            kcRequest.setLastName(updateCustomer.getLastName());
            kcRequest.setEmail(updateCustomer.getEmail());

            keycloakService.updateKeycloakUser(keycloakId, kcRequest);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar usuário no Keycloak: " + e.getMessage());
        }
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
