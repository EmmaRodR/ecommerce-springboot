package com.ecommercealimentacion.Ecommerce.Alimentacion.services;

import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.NoElementException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.UsernameNotFound;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.cart.AddItemRequestDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.cart.CartDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.cart.DeleteItemRequestDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.cart.UpdateItemRequestDto;

public interface IGuestCartService {

    
    CartDto getGuestCart (String sessionId) throws UsernameNotFound;

    CartDto addProductToGuestCart (String sessionId,AddItemRequestDto addItemRequestDto) throws UsernameNotFound, NoElementException, ElementAlreadyExistsException;

    CartDto removeProductOfGuestCart (String sessionId, DeleteItemRequestDto deleteItemRequestDto) throws UsernameNotFound, NoElementException;

    CartDto updateProductFromGuestCart (String sessionId,UpdateItemRequestDto updateItemRequestDto) throws UsernameNotFound, NoElementException;

    CartDto mergeGuestCartToUser (String sessionId, Long userId);

    
}
