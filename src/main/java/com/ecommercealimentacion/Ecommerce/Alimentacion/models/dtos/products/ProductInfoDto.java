package com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.products;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class ProductInfoDto {

    private Long id;

    private String name;

    private String category;

    private BigDecimal price;

    private String imgUrl;

    public ProductInfoDto () {}

    public ProductInfoDto(Long id, String name, String category, BigDecimal price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    

}
