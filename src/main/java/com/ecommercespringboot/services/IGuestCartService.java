package com.ecommercespringboot.services;

import com.ecommercespringboot.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercespringboot.exceptions.especificExceptions.NoElementException;
import com.ecommercespringboot.exceptions.especificExceptions.UsernameNotFound;
import com.ecommercespringboot.models.dtos.cart.AddItemRequestDto;
import com.ecommercespringboot.models.dtos.cart.CartDto;
import com.ecommercespringboot.models.dtos.cart.DeleteItemRequestDto;
import com.ecommercespringboot.models.dtos.cart.UpdateItemRequestDto;

public interface IGuestCartService {

    
    CartDto getGuestCart (String sessionId) throws UsernameNotFound;

    CartDto addProductToGuestCart (String sessionId,AddItemRequestDto addItemRequestDto) throws UsernameNotFound, NoElementException, ElementAlreadyExistsException;

    CartDto removeProductOfGuestCart (String sessionId, DeleteItemRequestDto deleteItemRequestDto) throws UsernameNotFound, NoElementException;

    CartDto updateProductFromGuestCart (String sessionId,UpdateItemRequestDto updateItemRequestDto) throws UsernameNotFound, NoElementException;

    CartDto mergeGuestCartToUser (String sessionId, Long userId);

    
}
