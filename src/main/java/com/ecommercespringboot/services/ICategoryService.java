package com.ecommercespringboot.services;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.ecommercespringboot.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercespringboot.exceptions.especificExceptions.NoElementException;
import com.ecommercespringboot.models.dtos.categories.CategoryRequestDto;
import com.ecommercespringboot.models.dtos.categories.CategoryResponseDto;
import com.ecommercespringboot.models.dtos.responses.BaseResponseDto;
import com.ecommercespringboot.models.dtos.responses.PageResponse;

public interface ICategoryService {

    PageResponse get (Pageable page) throws NoElementException;

    ResponseEntity<BaseResponseDto<CategoryResponseDto>> create (CategoryRequestDto categoryRequestDto) throws ElementAlreadyExistsException;

    ResponseEntity<BaseResponseDto<CategoryResponseDto>> update (Long id, CategoryRequestDto categoryRequestDto) throws NoElementException;

    ResponseEntity<?> delete (Long id) throws NoElementException;

    CategoryResponseDto getCategoryById(Long id) throws NoElementException;


}
