package com.ecommercespringboot.services.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ecommercespringboot.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercespringboot.exceptions.especificExceptions.NoElementException;
import com.ecommercespringboot.exceptions.especificExceptions.UsernameNotFound;
import com.ecommercespringboot.models.dtos.cart.AddItemRequestDto;
import com.ecommercespringboot.models.dtos.cart.CartDto;
import com.ecommercespringboot.models.dtos.cart.DeleteItemRequestDto;
import com.ecommercespringboot.models.dtos.cart.UpdateItemRequestDto;
import com.ecommercespringboot.models.entities.Cart;
import com.ecommercespringboot.models.entities.CartItem;
import com.ecommercespringboot.models.entities.Product;
import com.ecommercespringboot.models.entities.User;
import com.ecommercespringboot.repositories.ICartItemRepository;
import com.ecommercespringboot.repositories.ICartRepository;
import com.ecommercespringboot.repositories.IProductRepository;
import com.ecommercespringboot.repositories.UserRepository;
import com.ecommercespringboot.services.ICartService;

@Service
public class CartServiceImpl implements ICartService {

    private ICartRepository cartRepository;

    private ICartItemRepository cartItemRepository;

    private UserRepository userRepository;

    private IProductRepository productRepository;

    private ModelMapper modelMapper;

    public CartServiceImpl(ModelMapper modelMapper, ICartItemRepository cartItemRepository,
            ICartRepository cartRepository, UserRepository userRepository, IProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CartDto getCart(Long userId) throws UsernameNotFound {

        Cart cart = cartRepository.findByuserId(userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFound("Not found a user with the id: " + userId));

        // Crea el carrito si no existe
        if (cart == null) {
            Cart newCart = new Cart();
            newCart.setUser(user);
            cartRepository.save(newCart);

            return modelMapper.map(newCart, CartDto.class);
        }

        return modelMapper.map(cart, CartDto.class);
    }

    @Override
    public CartDto addProductToCart(Long userId, AddItemRequestDto addItemRequestDto)
            throws UsernameNotFound, NoElementException, ElementAlreadyExistsException {

        Cart cart = cartRepository.findByuserId(userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFound("Not found a user with the id: " + userId));

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }

        Product product = productRepository.findById(addItemRequestDto.getProductId())
                .orElseThrow(() -> new NoElementException("The product not exists"));

        Optional<CartItem> existingCartItem = cartItemRepository.findByProductIdAndCartId(product.getId(),
                cart.getId());

        if (existingCartItem.isPresent()) {
            throw new ElementAlreadyExistsException("The product is already in the cart.");
        }

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .product(product)
                .quantity(addItemRequestDto.getQuantity())
                .build();

        cartItem.setTotalAmount(cartItem.getTotalAmount());
        cartItemRepository.save(cartItem);

        cart.getItems().add(cartItem);
        cart.setQuantity(cart.getQuantity() + cartItem.getQuantity());
        recalculateCartTotals(cart);

        cartRepository.save(cart);

        return modelMapper.map(cart, CartDto.class);

    }

    @Override
    public CartDto removeProductInCart(Long userId, DeleteItemRequestDto deleteItemRequestDto)
            throws UsernameNotFound, NoElementException {

        // Search for cart user
        Cart cart = cartRepository.findByuserId(userId);

        if (cart == null) {
            throw new NoElementException("No cart found for user ID: " + userId);
        }

        CartItem cartItem = cartItemRepository
                .findByProductIdAndCartId(deleteItemRequestDto.getProductId(), cart.getId())
                .orElseThrow(() -> new NoElementException("The item not exists in the cart"));

        cart.setQuantity(cart.getQuantity() - cartItem.getQuantity());
        recalculateCartTotals(cart);

        cart.getItems().removeIf(item -> item.getProduct().getId().equals(deleteItemRequestDto.getProductId()));

        cartItemRepository.deleteById(cartItem.getId());

        cartRepository.save(cart);

        return modelMapper.map(cart, CartDto.class);
    }

    @Override
    public CartDto updateProductFromCart(Long userId, UpdateItemRequestDto updateItemRequestDto)
            throws UsernameNotFound, NoElementException {

        Cart cart = cartRepository.findByuserId(userId);
        CartItem cartItem = cartItemRepository
                .findByProductIdAndCartId(updateItemRequestDto.getProductId(), cart.getId())
                .orElseThrow(() -> new NoElementException("The item not exists in the cart"));

        // Actualiza cantidad y importe total del CartItem
        cartItem.setQuantity(updateItemRequestDto.getQuantity());
        BigDecimal price = cartItem.getProduct().getPrice();
        BigDecimal restAmount = price.multiply(BigDecimal.valueOf(cartItem.getQuantity()));
        double restAmountDouble = restAmount.doubleValue();
        cartItem.setTotalAmount(restAmountDouble);

        cartItemRepository.save(cartItem);

        recalculateCartTotals(cart);

        cartRepository.save(cart);

        return modelMapper.map(cart, CartDto.class);

    }

    private void recalculateCartTotals(Cart cart) {

        double totalCartAmount = 0.0;

        for (CartItem item : cart.getItems()) {
            totalCartAmount += item.getTotalAmount();
        }

        cart.setTotalAmount(totalCartAmount);
    }

}
