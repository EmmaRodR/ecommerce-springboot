package com.ecommercealimentacion.Ecommerce.Alimentacion.services.impl;

import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.NoElementException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.categories.CategoryRequestDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.categories.CategoryResponseDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.responses.BaseResponseDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.responses.PageResponse;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.Category;
import com.ecommercealimentacion.Ecommerce.Alimentacion.repositories.ICategoryRepository;
import com.ecommercealimentacion.Ecommerce.Alimentacion.repositories.IProductRepository;
import com.ecommercealimentacion.Ecommerce.Alimentacion.services.ICategoryService;
import com.ecommercealimentacion.Ecommerce.Alimentacion.utils.mappers.CategoryMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {


    ICategoryRepository categoryRepository;
    IProductRepository productRepository;


    CategoryMapper mapper;

    public CategoryServiceImpl (IProductRepository productRepository,ICategoryRepository categoryRepository, CategoryMapper mapper)
    {
        this.mapper = mapper;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PageResponse get(Pageable page) throws NoElementException {

        Page<Category> categoriesInBd = categoryRepository.findAll(page);

        if (categoriesInBd.isEmpty()) {
            throw new NoElementException("List of elements is empty");
        }

        List<CategoryResponseDto> categoryDtos = categoriesInBd.getContent().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

        return PageResponse.builder()
                .pageNumber(categoriesInBd.getPageable().getPageNumber())
                .pageSize(categoriesInBd.getPageable().getPageSize())
                .numberOfElements(categoriesInBd.getNumberOfElements())
                .content(categoryDtos)
                .build();

    }


    @Override
    public ResponseEntity<BaseResponseDto<CategoryResponseDto>> create(CategoryRequestDto categoryRequestDto) throws ElementAlreadyExistsException {

        String categoryName = categoryRequestDto.getName();
        Optional<Category> categoryInBd = categoryRepository.findByName(categoryName);

        if (categoryInBd.isPresent()) {
            throw new ElementAlreadyExistsException("The element with the name: " + categoryName + " already exists");
        }

        Category categoryCreated = categoryRepository.save(mapper.toEntity(categoryRequestDto));


        return BaseResponseDto.build(mapper.toDto(categoryCreated),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BaseResponseDto<CategoryResponseDto>> update(Long id, CategoryRequestDto categoryRequestDto) throws NoElementException {

        Optional<Category> categoryInBd = categoryRepository.findById(id);

        if (categoryInBd.isEmpty()) {
            throw new NoElementException("Not exists a element with the id: " + id);
        }

        Category categoryToUpdate = categoryInBd.get();
        categoryToUpdate.setName(categoryRequestDto.getName());

        CategoryResponseDto finalCategoryDto = mapper.toDto(categoryRepository.save(categoryToUpdate));

        return BaseResponseDto.build(finalCategoryDto,HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> delete (Long id) throws NoElementException {

        Optional<Category> categoryToDelete = Optional.ofNullable(categoryRepository.findById(id)
                .orElseThrow(() -> new NoElementException("Element not exists")));

         categoryRepository.deleteById(categoryToDelete.get().getId());

         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


    }

     @Override
    public CategoryResponseDto getCategoryById(Long id) throws NoElementException {
    
        Category categoryInBd = categoryRepository.findById(id).
                            orElseThrow(() -> new NoElementException("Product with the id " + id + " not was found"));
       
        return mapper.toDto(categoryInBd);
    }
}
