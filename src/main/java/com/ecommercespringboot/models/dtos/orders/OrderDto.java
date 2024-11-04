package com.ecommercespringboot.models.dtos.orders;

import java.math.BigDecimal;
import java.util.List;

import com.ecommercespringboot.models.dtos.products.ProductOrderInfoDto;
import com.ecommercespringboot.models.enums.OrderStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;

@Builder
public class OrderDto {

    private Long id;

    private String userName;

    private List<ProductOrderInfoDto> products;

    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public OrderDto(Long id, String userName, List<ProductOrderInfoDto> products, BigDecimal totalAmount,OrderStatus status) {
        this.id = id;
        this.userName = userName;
        this.products = products;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public OrderDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<ProductOrderInfoDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductOrderInfoDto> products) {
        this.products = products;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    
}
