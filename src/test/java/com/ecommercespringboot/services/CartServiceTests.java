package com.ecommercespringboot.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.ecommercespringboot.exceptions.especificExceptions.UsernameNotFound;
import com.ecommercespringboot.models.dtos.cart.AddItemRequestDto;
import com.ecommercespringboot.models.dtos.cart.CartDto;
import com.ecommercespringboot.models.entities.Cart;
import com.ecommercespringboot.models.entities.CartItem;
import com.ecommercespringboot.models.entities.Product;
import com.ecommercespringboot.models.entities.User;
import com.ecommercespringboot.repositories.ICartItemRepository;
import com.ecommercespringboot.repositories.ICartRepository;
import com.ecommercespringboot.repositories.IProductRepository;
import com.ecommercespringboot.repositories.UserRepository;
import com.ecommercespringboot.services.impl.CartServiceImpl;

public class CartServiceTests {

    @Mock
    private ICartRepository cartRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private IProductRepository productRepository;

    @Mock
    private ICartItemRepository cartItemRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    private User testUser;
    private Product testProduct;
    private Cart testCart;
    private AddItemRequestDto addItemRequestDto;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setup () {
        MockitoAnnotations.openMocks(this);
        reset(userRepository); // Esto limpiará las invocaciones previas de los mocks
        reset(cartRepository); // Esto limpiará las invocaciones previas de los mocks
        reset(cartItemRepository); // Esto limpiará las invocaciones previas de los mocks

        testUser = new User();
        testUser.setUsername("testuser");
        testUser.setId(1L);
        testUser.setEmail("testuser@email.com");


        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setPrice(BigDecimal.valueOf(100));

        testCart = new Cart();
        testCart.setUser(testUser);

        addItemRequestDto = new AddItemRequestDto();
        addItemRequestDto.setProductId(1L);
        addItemRequestDto.setQuantity(1);

    }   

    @Test
    void getCart_CartExists_ReturnsCartDto() throws UsernameNotFound {

        Cart existingCart = new Cart();
        existingCart.setUser(testUser);

        CartDto expectedCartDto = new CartDto(); // Configura el CartDto esperado

        when(userRepository.findById(testUser.getId())).thenReturn(Optional.of(testUser));
        when(cartRepository.findByuserId(testUser.getId())).thenReturn(existingCart);
        when(modelMapper.map(existingCart, CartDto.class)).thenReturn(expectedCartDto); // Mock del ModelMapper

        CartDto result = cartService.getCart(testUser.getId());

        assertNotNull(result);
        assertEquals(expectedCartDto, result);
        assertEquals(expectedCartDto.getUserName(), result.getUserName());
        verify(cartRepository, never()).save(any(Cart.class));
    }

     @Test
    void addProductToCart_CreatesNewCartAndAddsProduct() throws Exception {
        when(userRepository.findById(testUser.getId())).thenReturn(Optional.of(testUser));
        when(cartRepository.findByuserId(testUser.getId())).thenReturn(null); // Simula que no existe el carrito
        when(productRepository.findById(addItemRequestDto.getProductId())).thenReturn(Optional.of(testProduct));
        when(cartItemRepository.findByProductIdAndCartId(testProduct.getId(), testCart.getId()))
                .thenReturn(Optional.empty());
        when(modelMapper.map(any(Cart.class), eq(CartDto.class))).thenReturn(new CartDto());

        CartDto result = cartService.addProductToCart(testUser.getId(), addItemRequestDto);

        assertNotNull(result);
        verify(cartRepository, times(2)).save(any(Cart.class)); // Verifica que se crea el carrito
        verify(cartItemRepository).save(any(CartItem.class)); // Verifica que se agrega el producto
    }
    
}
