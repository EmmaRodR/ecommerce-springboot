package com.ecommercespringboot.services;


import com.ecommercespringboot.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercespringboot.exceptions.especificExceptions.NoElementException;
import com.ecommercespringboot.models.dtos.products.ProductRequestDto;
import com.ecommercespringboot.models.dtos.products.ProductResponseDto;
import com.ecommercespringboot.models.dtos.responses.BaseResponseDto;
import com.ecommercespringboot.models.entities.Category;
import com.ecommercespringboot.models.entities.Product;
import com.ecommercespringboot.repositories.ICategoryRepository;
import com.ecommercespringboot.repositories.IProductRepository;
import com.ecommercespringboot.services.impl.ProductServiceImpl;
import com.ecommercespringboot.utils.mappers.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private IProductRepository productRepository;

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private ProductMapper mapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private Category category;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        category = new Category();
        category.setId(1L);
        category.setName("Electronics");
    }

 

    @Test
    void testGet_whenProductsDoNotExist_shouldThrowNoElementException() {
        when(productRepository.findAll(any(PageRequest.class))).thenReturn(Page.empty());

        assertThrows(NoElementException.class, () -> productService.get(PageRequest.of(0, 10)));
    }

    @Test
    void testGetProductsByCategory_whenCategoryExists() throws NoElementException {
        when(categoryRepository.findByName("Electronics")).thenReturn(Optional.of(category));
        when(productRepository.findByCategory(anyLong())).thenReturn(List.of(new Product()));
        when(mapper.toDto(any(Product.class))).thenReturn(new ProductResponseDto());



        List<ProductResponseDto> products = productService.getProductsByCategory("Electronics");

        assertNotNull(products);
        assertEquals(1, products.size());
    }

    @Test
    void testGetProductsByCategory_whenCategoryDoesNotExist_shouldThrowNoElementException() {
        when(categoryRepository.findByName("NonExisting")).thenReturn(Optional.empty());

        assertThrows(NoElementException.class, () -> productService.getProductsByCategory("NonExisting"));
    }

    @Test
    void testCreate_whenProductDoesNotExist() throws ElementAlreadyExistsException, NoElementException {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Laptop");
        requestDto.setPrice(BigDecimal.valueOf(1200.0));
        requestDto.setCategoryName("Electronics");


        Category category = new Category();
        Product product = new Product();
        when(productRepository.findByName(requestDto.getName().toLowerCase())).thenReturn(Optional.empty());
        when(categoryRepository.findByName(requestDto.getCategoryName())).thenReturn(Optional.of(category));
        when(mapper.toEntity(any(ProductRequestDto.class), any(Category.class))).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(mapper.toDto(any(Product.class))).thenReturn(new ProductResponseDto());

        ResponseEntity<BaseResponseDto<ProductResponseDto>> response = productService.create(requestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testCreate_whenProductAlreadyExists_shouldThrowElementAlreadyExistsException() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Laptop");
        requestDto.setPrice(BigDecimal.valueOf(1200.0));
        requestDto.setCategoryName("Electronics");
        when(productRepository.findByName(requestDto.getName().toLowerCase())).thenReturn(Optional.of(new Product()));

        assertThrows(ElementAlreadyExistsException.class, () -> productService.create(requestDto));
    }

    @Test
    void testDelete_whenProductExists() throws NoElementException {
        Product product = new Product();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).delete(product);
        when(mapper.toDto(any(Product.class))).thenReturn(new ProductResponseDto());

        ResponseEntity<?> response = productService.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDelete_whenProductDoesNotExist_shouldThrowNoElementException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoElementException.class, () -> productService.delete(1L));
    }

    @Test
    void testUpdate_whenProductAndCategoryExist() throws NoElementException {
        Product product = new Product();
        Category category = new Category();
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Laptop");
        requestDto.setPrice(BigDecimal.valueOf(1200.0));
        requestDto.setCategoryName("Electronics");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(categoryRepository.findByName(requestDto.getCategoryName())).thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(mapper.toDto(any(Product.class))).thenReturn(new ProductResponseDto());

        ResponseEntity<BaseResponseDto<ProductResponseDto>> response = productService.update(1L, requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetProductsById_whenProductExists() throws NoElementException {
        Product product = new Product();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(mapper.toDto(any(Product.class))).thenReturn(new ProductResponseDto());

        ProductResponseDto response = productService.getProductsById(1L);

        assertNotNull(response);
    }

    @Test
    void testGetProductsById_whenProductDoesNotExist_shouldThrowNoElementException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoElementException.class, () -> productService.getProductsById(1L));
    }
}