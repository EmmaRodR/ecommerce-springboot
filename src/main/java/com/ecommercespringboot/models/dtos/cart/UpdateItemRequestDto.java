package com.ecommercespringboot.models.dtos.cart;

import lombok.Data;

@Data
public class UpdateItemRequestDto {
    
    private Long productId;
    private int quantity;

}
