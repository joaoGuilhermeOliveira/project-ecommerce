package com.ecommerce.store.web.dtos.request;

import com.ecommerce.store.entities.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class SaleDto {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date SaleData;

    private String saleValue;

    private String freightPrice;

    private String totalPice;

    private CustomerDto customer;

}
