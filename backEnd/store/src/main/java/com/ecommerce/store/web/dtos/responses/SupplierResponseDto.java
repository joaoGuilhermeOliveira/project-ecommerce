package com.ecommerce.store.web.dtos.responses;

import com.ecommerce.store.entities.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SupplierResponseDto {

    @NotBlank(message = "CNPJ é obrigatório")
    private String cnpj;
    @NotBlank(message = "Nome é obrigatório")
    private String name;
    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "Email inválido")
    private String email;

}
