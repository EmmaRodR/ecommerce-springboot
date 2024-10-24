package com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.products;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductOrderInfoDto {


    private String productName;

    private int quantity;

    private BigDecimal totalAmount;



    
}
