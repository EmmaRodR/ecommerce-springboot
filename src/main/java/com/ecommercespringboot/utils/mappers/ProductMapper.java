package com.ecommercespringboot.utils.mappers;

import org.springframework.stereotype.Component;

import com.ecommercespringboot.models.dtos.products.ProductRequestDto;
import com.ecommercespringboot.models.dtos.products.ProductResponseDto;
import com.ecommercespringboot.models.entities.Category;
import com.ecommercespringboot.models.entities.Product;


@Component
public class ProductMapper {


    public ProductResponseDto toDto (Product product) {

        if (product == null) {
            return null;
        }

        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName().toUpperCase());
        dto.setPrice(product.getPrice());
        dto.setCategoryName(product.getCategory().getName().toUpperCase());
        dto.setImgUrl(product.getImgUrl());

        return dto;
    }

    public Product toEntity (ProductRequestDto productRequestDto, Category category) {

        if (productRequestDto == null) {
            return null;
        }

        Product product = new Product();
        product.setName(productRequestDto.getName().toUpperCase());
        product.setPrice(productRequestDto.getPrice());
        product.setCategory(category);


        return product;

    }













}
