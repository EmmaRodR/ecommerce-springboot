package com.ecommercealimentacion.Ecommerce.Alimentacion.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.NoElementException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.UsernameNotFound;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.cart.AddItemRequestDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.cart.CartDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.cart.DeleteItemRequestDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.cart.UpdateItemRequestDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.services.ICartService;
import com.ecommercealimentacion.Ecommerce.Alimentacion.services.IGuestCartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/v1/cart")
@Tag(name = "Cart Controller",description = "Manage the Cart")
public class CartController {

    private ICartService cartService;

    private IGuestCartService guestCartService;


    public CartController (ICartService cartService,IGuestCartService guestCartService) {
        this.cartService = cartService;
        this.guestCartService = guestCartService;
    }


    @GetMapping
    @Operation(summary = "Get Cart", description = "Get user Cart")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<CartDto> getCart(@Valid @RequestParam(required = false) Long userId,@RequestParam(required = false) String sessionId) throws UsernameNotFound{
      
        CartDto cartDto;

        if (userId != null) {
            cartDto = cartService.getCart(userId);
        } else if (sessionId != null) {
            cartDto = guestCartService.getGuestCart(sessionId);
        } else {
            throw new IllegalArgumentException("userId or sessioniD must be provided");
        }

        return ResponseEntity.ok().body(cartDto);

        
    }

    @PostMapping
    @Operation(summary = "Add Product", description = "Add a product to user Cart")
    @SecurityRequirement(name = "Bearer Authentication")

    public ResponseEntity<CartDto> addProductoToCart(@Valid @RequestParam(required = false) Long userId,@Valid @RequestParam(required = false) String sessionId,@Valid @RequestBody AddItemRequestDto addItemRequestDto) throws UsernameNotFound, NoElementException, ElementAlreadyExistsException {
        
        CartDto cartDto;

        if (userId != null) {
            cartDto = cartService.addProductToCart(userId, addItemRequestDto);
        } else if (sessionId != null) {
            cartDto = guestCartService.addProductToGuestCart(sessionId, addItemRequestDto);
        } else {
            throw new IllegalArgumentException("userId or sessioniD must be provided");
        }

        return ResponseEntity.ok().body(cartDto);
    }

    @DeleteMapping
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Remove Product", description = "Remove product of Cart")
    public CartDto removeProductoFromCart(@Valid @RequestParam(required = false) Long userId,@Valid @RequestParam(required = false) String sessionId, @RequestBody DeleteItemRequestDto deleteItemRequestDto) throws UsernameNotFound, NoElementException {
       
        if (userId != null) {
            return cartService.removeProductInCart(userId, deleteItemRequestDto);
        } else if (sessionId != null) {
            return guestCartService.removeProductOfGuestCart(sessionId, deleteItemRequestDto);
        } else {
            throw new IllegalArgumentException("userId or sessioniD must be provided");
        }
    
        
    }

    @PatchMapping
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Update product", description = "Update product of Cart")
    public CartDto updateProductoInCart(@Valid @RequestParam(required = false) Long userId,
            @Valid @RequestParam(required = false) String sessionId,
            @RequestBody UpdateItemRequestDto updateItemRequestDto) throws UsernameNotFound, NoElementException {

        if (userId != null) {
            return cartService.updateProductFromCart(userId, updateItemRequestDto);
        } else if (sessionId != null) {
            return guestCartService.updateProductFromGuestCart(sessionId, updateItemRequestDto);
        } else {
            throw new IllegalArgumentException("userId or sessioniD must be provided");
        }

    }

    @GetMapping("/merge/{userId}/{sessionId}")
    @Operation(summary = "Merge Carts", description = "Add a product to user Cart")
    @SecurityRequirement(name = "Bearer Authentication")
    public CartDto mergeGuestCartToUser(
            @Valid @PathVariable(required = false) Long userId,
            @Valid @PathVariable(required = false) String sessionId) {

        return guestCartService.mergeGuestCartToUser(sessionId, userId);

    }

}