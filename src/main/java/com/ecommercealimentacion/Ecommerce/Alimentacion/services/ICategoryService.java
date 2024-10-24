package com.ecommercealimentacion.Ecommerce.Alimentacion.services;

import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.NoElementException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.categories.CategoryRequestDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.categories.CategoryResponseDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.responses.BaseResponseDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.responses.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {

    PageResponse get (Pageable page) throws NoElementException;

    ResponseEntity<BaseResponseDto<CategoryResponseDto>> create (CategoryRequestDto categoryRequestDto) throws ElementAlreadyExistsException;

    ResponseEntity<BaseResponseDto<CategoryResponseDto>> update (Long id, CategoryRequestDto categoryRequestDto) throws NoElementException;

    ResponseEntity<?> delete (Long id) throws NoElementException;

    CategoryResponseDto getCategoryById(Long id) throws NoElementException;


}
