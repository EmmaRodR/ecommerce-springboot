package com.ecommercealimentacion.Ecommerce.Alimentacion.services;

import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.NoElementException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.UsernameNotFound;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.cart.AddItemRequestDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.cart.CartDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.cart.DeleteItemRequestDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.cart.UpdateItemRequestDto;



public interface ICartService {


    CartDto getCart (Long id) throws UsernameNotFound;

    CartDto addProductToCart (Long userId,AddItemRequestDto addItemRequestDto) throws UsernameNotFound, NoElementException, ElementAlreadyExistsException;

    CartDto removeProductInCart (Long userId, DeleteItemRequestDto deleteItemRequestDto) throws UsernameNotFound, NoElementException;

    CartDto updateProductFromCart (Long userId,UpdateItemRequestDto updateItemRequestDto) throws UsernameNotFound, NoElementException;



}