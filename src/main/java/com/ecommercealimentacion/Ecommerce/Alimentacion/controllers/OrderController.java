package com.ecommercealimentacion.Ecommerce.Alimentacion.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.NoElementException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.UsernameNotFound;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.orders.OrderDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.services.IOrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/orders")
@CrossOrigin(origins = "https://ecommerce-reactfrontend.onrender.com/")
@Tag(name = "Order Controller",description = "Create orders")
public class OrderController {

    IOrderService orderService;

    public OrderController (IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{userId}")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Create Order", description = "Create a Order")
    @CrossOrigin(origins = "https://ecommerce-reactfrontend.onrender.com/")
    public OrderDto createOrder (@PathVariable Long userId) throws UsernameNotFound, ElementAlreadyExistsException {
        return orderService.createOrder(userId);
    }

    @GetMapping("/{userId}")
    @SecurityRequirement(name = "Bearer Authentication")
    @CrossOrigin(origins = "https://ecommerce-reactfrontend.onrender.com/")
    @Operation(summary = "Get Order By UserId", description = "Get an Order by UserId")
    public OrderDto getOrderByUserId (@PathVariable Long userId) throws UsernameNotFound, NoElementException {
        return orderService.getOrderByUserId(userId);
    }

    @PatchMapping("/{userId}")
    @CrossOrigin(origins = "https://ecommerce-reactfrontend.onrender.com/")
    @Operation(summary = "Update Order", description = "Update a order")
    @SecurityRequirement(name = "Bearer Authentication")
    public OrderDto updateOrder (@PathVariable Long userId, @Valid @RequestParam int quantity) throws UsernameNotFound {
        return orderService.updateOrder(userId,quantity);
    }

    @DeleteMapping("/{userId}")
    @CrossOrigin(origins = "https://ecommerce-reactfrontend.onrender.com/")
    @Operation(summary = "Delete Order", description = "Delete a order")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> deleteOrderDto (@PathVariable Long userId) throws UsernameNotFound {
        return orderService.deleteOrder(userId);
    }




    
}
