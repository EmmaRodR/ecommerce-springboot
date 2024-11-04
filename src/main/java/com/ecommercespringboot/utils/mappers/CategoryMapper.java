package com.ecommercespringboot.utils.mappers;

import org.springframework.stereotype.Component;

import com.ecommercespringboot.models.dtos.categories.CategoryRequestDto;
import com.ecommercespringboot.models.dtos.categories.CategoryResponseDto;
import com.ecommercespringboot.models.entities.Category;
import com.ecommercespringboot.repositories.ICategoryRepository;

@Component
public class CategoryMapper {

    ICategoryRepository categoryRepository;

    public CategoryMapper(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Responses

    public CategoryResponseDto toDto(Category category) {

        if (category == null) {
            return null;
        }

        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setName(category.getName().toUpperCase());

        return dto;

    }

    public Category toEntity(CategoryRequestDto categoryRequestDto) {

        if (categoryRequestDto == null) {
            return null;
        }

        Category categoryBd = new Category();
        categoryBd.setName(categoryRequestDto.getName().toUpperCase());

        return categoryBd;

    }

}
