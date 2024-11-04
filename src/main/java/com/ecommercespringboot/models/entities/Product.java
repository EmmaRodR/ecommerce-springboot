package com.ecommercespringboot.models.entities;


import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull(message = "Name not be null")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Price must not be null")
    @Positive(message = "Price must be positive")
    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne()
    @JoinColumn(name = "categorie_id")
    @JsonIgnore
    private Category category;

    @Column(name = "img_url")
    private String imgUrl;


    public Product(Long id, String name, BigDecimal price, Category category,String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.imgUrl = imgUrl;
    }

    public Product(String name, BigDecimal price, Category category, String imgUrl) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.imgUrl = imgUrl;
    }


    public Product() {}

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
}

