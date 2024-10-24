package com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.cart;

import lombok.Data;

@Data
public class UpdateItemRequestDto {
    
    private Long productId;
    private int quantity;

}
