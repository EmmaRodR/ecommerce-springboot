package com.ecommercealimentacion.Ecommerce.Alimentacion.services.impl;

import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.NoElementException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.products.ProductRequestDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.products.ProductResponseDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.responses.BaseResponseDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.responses.PageResponse;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.Category;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.Product;
import com.ecommercealimentacion.Ecommerce.Alimentacion.repositories.ICategoryRepository;
import com.ecommercealimentacion.Ecommerce.Alimentacion.repositories.IProductRepository;
import com.ecommercealimentacion.Ecommerce.Alimentacion.services.IProductService;
import com.ecommercealimentacion.Ecommerce.Alimentacion.utils.mappers.ProductMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {

    IProductRepository productRepository;
    ICategoryRepository categoryRepository;
    ProductMapper mapper;

    public ProductServiceImpl(IProductRepository productRepository, ICategoryRepository categoryRepository,
            ProductMapper mapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public PageResponse get(Pageable page) throws NoElementException {

        Page<Product> products = productRepository.findAll(page);

        if (products.isEmpty()) {
            throw new NoElementException("The element list is empty");
        }

        return PageResponse.builder()
                .pageNumber(products.getPageable().getPageNumber())
                .pageSize(products.getPageable().getPageSize())
                .numberOfElements(products.getNumberOfElements())
                .content(products.getContent().stream().map(mapper::toDto).collect(Collectors.toList()))
                .build();
    }

    // No utilizado de momento en el frontend
    @Override
    public List<ProductResponseDto> getProductsByCategory(String name) throws NoElementException {

        Category categoryInBd = categoryRepository.findByName(name)
                .orElseThrow(() -> new NoElementException("Category with the name: " + name + " not exists"));

        List<Product> productsInCategory = productRepository.findByCategory(categoryInBd.getId());

        List<ProductResponseDto> productResponseDtos = productsInCategory
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

        return productResponseDtos;
    }

    // No utilizado de momento en el frontend
    @Override
    public List<ProductResponseDto> getProductsByName(String name) throws NoElementException {

        List<Product> productInBd = productRepository.findByNameFindCoincidence(name);

        if (productInBd.isEmpty()) {
            throw new NoElementException("Not exists a product with the name: " + name);
        }

        return productInBd
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<BaseResponseDto<ProductResponseDto>> create(ProductRequestDto productRequestDto)
            throws ElementAlreadyExistsException, NoElementException {

        Optional<Product> productInBd = productRepository.findByName(productRequestDto.getName().toLowerCase());

        if (productInBd.isPresent()) {
            throw new ElementAlreadyExistsException(
                    "The element with the name: " + productRequestDto.getName() + " already exists");
        }

        Category category = categoryRepository.findByName(productRequestDto.getCategoryName())
                .orElseThrow(() -> new NoElementException("Category not exists"));

        Product savedProduct = productRepository.save(mapper.toEntity(productRequestDto, category));

        return BaseResponseDto.build(mapper.toDto(savedProduct), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BaseResponseDto<ProductResponseDto>> update(Long id, ProductRequestDto productRequestDto)
            throws NoElementException {

        String categoryName = productRequestDto.getCategoryName();

        Product productInBd = productRepository.findById(id)
                .orElseThrow(() -> new NoElementException("Not exists a element with the id: " + id));

        productInBd.setName(productRequestDto.getName());
        productInBd.setPrice(productRequestDto.getPrice());

        Category category = (categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new NoSuchElementException("Not exists a category with the name: " + categoryName)));

        productInBd.setCategory(category);

        productRepository.save(productInBd);

        return BaseResponseDto.build(mapper.toDto(productInBd), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> delete(Long id) throws NoElementException {

        Product productInBd = productRepository.findById(id)
                .orElseThrow(() -> new NoElementException("No exists element with the id: " + id));

        productRepository.delete(productInBd);

        return BaseResponseDto.build(mapper.toDto(productInBd), HttpStatus.NO_CONTENT);

    }

    @Override
    public ProductResponseDto getProductsById(Long id) throws NoElementException {
    
        Product productInBd = productRepository.findById(id).
                            orElseThrow(() -> new NoElementException("Product with the id " + id + " not was found"));
       
        return mapper.toDto(productInBd);
    }
}
