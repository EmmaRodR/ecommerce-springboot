package com.ecommercealimentacion.Ecommerce.Alimentacion.utils.mappers;

import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.categories.CategoryRequestDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.categories.CategoryResponseDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.Category;
import com.ecommercealimentacion.Ecommerce.Alimentacion.repositories.ICategoryRepository;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class CategoryMapper {

    ICategoryRepository categoryRepository;

    public CategoryMapper (ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //Responses

    public CategoryResponseDto toDto (Category category) {

        if (category == null) {
            return null;
        }

        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setName(category.getName().toUpperCase(Locale.ROOT));


        return dto;

    }


    public Category toEntity (CategoryRequestDto categoryRequestDto) {


        if (categoryRequestDto == null) {
            return null;
        }

        Category categoryBd = new Category();
        categoryBd.setName(categoryRequestDto.getName().toUpperCase(Locale.ROOT));


        return categoryBd;


    }



}
