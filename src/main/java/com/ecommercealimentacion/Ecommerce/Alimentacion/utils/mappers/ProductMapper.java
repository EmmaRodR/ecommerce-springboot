package com.ecommercealimentacion.Ecommerce.Alimentacion.utils.mappers;

import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.products.ProductRequestDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.products.ProductResponseDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.Category;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.Product;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ProductMapper {


    public ProductResponseDto toDto (Product product) {

        if (product == null) {
            return null;
        }

        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName().toUpperCase(Locale.ROOT));
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
        product.setName(productRequestDto.getName().toUpperCase(Locale.ROOT));
        product.setPrice(productRequestDto.getPrice());
        product.setCategory(category);


        return product;

    }













}
