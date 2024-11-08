package com.ecommercespringboot.services;

import com.ecommercespringboot.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercespringboot.exceptions.especificExceptions.NoElementException;
import com.ecommercespringboot.models.dtos.categories.CategoryRequestDto;
import com.ecommercespringboot.models.dtos.categories.CategoryResponseDto;
import com.ecommercespringboot.models.dtos.responses.BaseResponseDto;
import com.ecommercespringboot.models.entities.Category;
import com.ecommercespringboot.repositories.ICategoryRepository;
import com.ecommercespringboot.repositories.IProductRepository;
import com.ecommercespringboot.services.impl.CategoryServiceImpl;
import com.ecommercespringboot.utils.mappers.CategoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTests {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private IProductRepository productRepository;

    @Mock
    private CategoryMapper mapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category;
    private CategoryRequestDto categoryRequestDto;
    private CategoryResponseDto categoryResponseDto;

    @BeforeEach
    public void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        categoryRequestDto = new CategoryRequestDto();
        categoryRequestDto.setName("Electronics");

        categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(1L);
        categoryResponseDto.setName("Electronics");
    }

    @Test
    public void testCreateCategory() throws ElementAlreadyExistsException {
        when(categoryRepository.findByName(any())).thenReturn(Optional.empty());
        when(mapper.toEntity(categoryRequestDto)).thenReturn(category);
        when(categoryRepository.save(any())).thenReturn(category);
        when(mapper.toDto(category)).thenReturn(categoryResponseDto);

        ResponseEntity<BaseResponseDto<CategoryResponseDto>> response = categoryService.create(categoryRequestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(categoryResponseDto, response.getBody().getData());
    }

    @Test
    public void testUpdateCategory() throws NoElementException {
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        when(categoryRepository.save(any())).thenReturn(category);
        when(mapper.toDto(category)).thenReturn(categoryResponseDto);

        ResponseEntity<BaseResponseDto<CategoryResponseDto>> response = categoryService.update(category.getId(), categoryRequestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryResponseDto, response.getBody().getData());
    }

    @Test
    public void testDeleteCategory() throws NoElementException {
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        doNothing().when(categoryRepository).deleteById(category.getId());

        ResponseEntity<?> response = categoryService.delete(category.getId());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(categoryRepository, times(1)).deleteById(category.getId());
    }

    @Test
    public void testGetCategoryById() throws NoElementException {
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        when(mapper.toDto(category)).thenReturn(categoryResponseDto);

        CategoryResponseDto response = categoryService.getCategoryById(category.getId());

        assertEquals(categoryResponseDto, response);
    }
}