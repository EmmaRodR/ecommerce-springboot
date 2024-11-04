package com.ecommercespringboot.models.dtos.cart;

import com.ecommercespringboot.models.dtos.products.ProductCartItemDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CartItemDto {

    @JsonIgnore
    private Long itemId;

    @Column(unique = true)
    private ProductCartItemDto product;

    private int quantity;

    private double totalAmount;

}
