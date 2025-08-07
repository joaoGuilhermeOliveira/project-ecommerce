package com.ecommerce.store.web.dtos.requests;

import com.ecommerce.store.enums.PaymentMethodEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.ecommerce.store.web.dtos.SaleItemDto;

import java.util.Date;
import java.util.List;

@Data
public class SaleRequestDto {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date SaleData;

    private String saleValue;

    private String freightPrice;

    private String totalPrice;

    private PaymentMethodEnum paymentMethod;

    private Long customerId;

    private List<SaleItemDto> items;

}