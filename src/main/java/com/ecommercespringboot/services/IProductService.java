package com.ecommercespringboot.services;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.ecommercespringboot.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercespringboot.exceptions.especificExceptions.NoElementException;
import com.ecommercespringboot.models.dtos.products.ProductRequestDto;
import com.ecommercespringboot.models.dtos.products.ProductResponseDto;
import com.ecommercespringboot.models.dtos.responses.BaseResponseDto;
import com.ecommercespringboot.models.dtos.responses.PageResponse;

import java.util.List;

public interface IProductService {


    PageResponse get (Pageable page) throws Exception ;

    ResponseEntity<BaseResponseDto<ProductResponseDto>> create (ProductRequestDto productRequestDto) throws ElementAlreadyExistsException, NoElementException;

    ResponseEntity<BaseResponseDto<ProductResponseDto>> update (Long id, ProductRequestDto productRequestDto) throws NoElementException;

    ResponseEntity<?> delete (Long id) throws  NoElementException;

    List<ProductResponseDto> getProductsByCategory(String name) throws  NoElementException;

    List<ProductResponseDto> getProductsByName(String name) throws  NoElementException;

    ProductResponseDto getProductsById(Long id) throws  NoElementException;

}
