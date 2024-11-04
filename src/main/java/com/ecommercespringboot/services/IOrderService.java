package com.ecommercespringboot.services;

import org.springframework.http.ResponseEntity;

import com.ecommercespringboot.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercespringboot.exceptions.especificExceptions.NoElementException;
import com.ecommercespringboot.exceptions.especificExceptions.UsernameNotFound;
import com.ecommercespringboot.models.dtos.orders.OrderDto;

public interface IOrderService {


    OrderDto createOrder (Long userId) throws UsernameNotFound,ElementAlreadyExistsException;

    OrderDto getOrderByUserId(Long userId) throws UsernameNotFound, NoElementException;

    OrderDto updateOrder(Long userId,int quantity) throws UsernameNotFound;

    ResponseEntity<?> deleteOrder(Long userId) throws UsernameNotFound;



    
}
