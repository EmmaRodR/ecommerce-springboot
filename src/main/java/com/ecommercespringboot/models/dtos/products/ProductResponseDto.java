package com.ecommercespringboot.models.dtos.products;

import java.math.BigDecimal;

public class ProductResponseDto {

    private Long id;

    private String name;

    private BigDecimal price;

    private String categoryName;

    private String imgUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductResponseDto(String name, BigDecimal price, String categoryName, String imgUrl) {
        this.name = name;
        this.price = price;
        this.categoryName = categoryName;
        this.imgUrl = imgUrl;
    }

    public ProductResponseDto() {
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    

}
