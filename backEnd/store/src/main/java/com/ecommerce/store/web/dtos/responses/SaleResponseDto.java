package com.ecommerce.store.web.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class SaleResponseDto {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date SaleData;

    private String saleValue;

    private String freightPrice;

    private String totalPrice;

}