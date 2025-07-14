package com.ecommerce.store.web.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class SaleRequestDto {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date SaleData;

    private String saleValue;

    private String freightPrice;

    private String totalPice;

    private CustomerRequestDto customer;

}
