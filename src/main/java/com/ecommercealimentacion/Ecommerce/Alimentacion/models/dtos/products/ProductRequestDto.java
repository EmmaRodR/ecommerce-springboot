package com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.products;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;

public class ProductRequestDto {

    @NotBlank
    private String name;

    private BigDecimal price;

    private String categoryName;


    public ProductRequestDto(String name, BigDecimal price, String categoryName) {
        this.name = name;
        this.price = price;
        this.categoryName = categoryName;
    }

    public ProductRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName (String categoryName) {
        this.categoryName = categoryName;
    }


}
