package com.ecommerce.store.enums;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class SaleItemId implements Serializable {

    private Long productId;
    private Long saleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaleItemId)) return false;
        SaleItemId that = (SaleItemId) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(saleId, that.saleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, saleId);
    }
}
