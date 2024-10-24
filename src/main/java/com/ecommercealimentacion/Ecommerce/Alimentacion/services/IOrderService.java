package com.ecommercealimentacion.Ecommerce.Alimentacion.services;

import org.springframework.http.ResponseEntity;

import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.NoElementException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.UsernameNotFound;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.orders.OrderDto;

public interface IOrderService {


    OrderDto createOrder (Long userId) throws UsernameNotFound,ElementAlreadyExistsException;

    OrderDto getOrderByUserId(Long userId) throws UsernameNotFound, NoElementException;

    OrderDto updateOrder(Long userId,int quantity) throws UsernameNotFound;

    ResponseEntity<?> deleteOrder(Long userId) throws UsernameNotFound;



    
}
