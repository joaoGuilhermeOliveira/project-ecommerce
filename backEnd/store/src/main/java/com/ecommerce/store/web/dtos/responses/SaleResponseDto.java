package com.ecommerce.store.web.dtos.responses;

import com.ecommerce.store.enums.PaymentMethodEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.ecommerce.store.web.dtos.SaleItemDto;

import java.util.Date;
import java.util.List;


@Data
public class SaleResponseDto {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date saleData;

    private String saleValue;

    private String freightPrice;

    private PaymentMethodEnum paymentMethod;

    private String totalPrice;

    private List<SaleItemDto> SaleItems;

}