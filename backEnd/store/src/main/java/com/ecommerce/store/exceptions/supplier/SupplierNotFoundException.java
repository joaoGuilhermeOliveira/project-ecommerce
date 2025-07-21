package com.ecommerce.store.exceptions.supplier;

public class SupplierNotFoundException extends RuntimeException {
    public SupplierNotFoundException(String mensagem) {
        super(mensagem);
    }
}