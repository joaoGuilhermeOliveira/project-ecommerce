package com.ecommerce.store.web.dtos.request;

import com.ecommerce.store.entities.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SupplierDto {

    @NotBlank(message = "CNPJ é obrigatório")
    private String cnpj;
    @NotBlank(message = "Nome é obrigatório")
    private String name;
    @NotBlank(message = "Telefone é obrigatório")
    private String phone_number;
    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "Email inválido")
    private String email;
    @NotBlank(message = "Endereço é obrigatório")
    private Address address;
}
