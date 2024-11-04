package com.ecommercespringboot.models.dtos.cart;

import java.util.List;

import lombok.Data;

@Data
public class CartDto {

    
    private String userName;

    private String sessionId;

    private List<CartItemDto> items;

    private double totalAmount;

}