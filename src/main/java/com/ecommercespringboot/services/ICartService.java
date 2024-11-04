package com.ecommercespringboot.services;

import com.ecommercespringboot.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercespringboot.exceptions.especificExceptions.NoElementException;
import com.ecommercespringboot.exceptions.especificExceptions.UsernameNotFound;
import com.ecommercespringboot.models.dtos.cart.AddItemRequestDto;
import com.ecommercespringboot.models.dtos.cart.CartDto;
import com.ecommercespringboot.models.dtos.cart.DeleteItemRequestDto;
import com.ecommercespringboot.models.dtos.cart.UpdateItemRequestDto;



public interface ICartService {


    CartDto getCart (Long id) throws UsernameNotFound;

    CartDto addProductToCart (Long userId,AddItemRequestDto addItemRequestDto) throws UsernameNotFound, NoElementException, ElementAlreadyExistsException;

    CartDto removeProductInCart (Long userId, DeleteItemRequestDto deleteItemRequestDto) throws UsernameNotFound, NoElementException;

    CartDto updateProductFromCart (Long userId,UpdateItemRequestDto updateItemRequestDto) throws UsernameNotFound, NoElementException;



}