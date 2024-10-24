package com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.products;

import java.util.List;

public class ProductByCategoryResponseDto {

    private Long id;

    private String name;

    private Double price;

    private String categoryName;

    private String imgUrl;

    private List<ProductResponseDto> products;


    public List<ProductResponseDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponseDto> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductByCategoryResponseDto(String name, Double price, String categoryName, String imgUrl) {
        this.name = name;
        this.price = price;
        this.categoryName = categoryName;
        this.imgUrl = imgUrl;
    }

    public ProductByCategoryResponseDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
