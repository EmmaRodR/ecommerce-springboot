package com.ecommercespringboot.models.dtos.cart;

import jakarta.persistence.Column;

public class AddItemRequestDto {

    @Column(unique = true)
    private Long productId;
    
    private int quantity;
    

    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    
}
