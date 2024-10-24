package com.ecommercealimentacion.Ecommerce.Alimentacion.services.impl;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.NoElementException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.UsernameNotFound;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.cart.AddItemRequestDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.cart.CartDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.cart.DeleteItemRequestDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.cart.UpdateItemRequestDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.Cart;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.CartItem;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.Product;
import com.ecommercealimentacion.Ecommerce.Alimentacion.repositories.ICartItemRepository;
import com.ecommercealimentacion.Ecommerce.Alimentacion.repositories.ICartRepository;
import com.ecommercealimentacion.Ecommerce.Alimentacion.repositories.IProductRepository;
import com.ecommercealimentacion.Ecommerce.Alimentacion.services.IGuestCartService;

@Service
public class GuestCartServiceImpl implements IGuestCartService{

    private ICartRepository cartRepository;

    private ICartItemRepository cartItemRepository;

    private IProductRepository productRepository;

    private ModelMapper modelMapper;



        public GuestCartServiceImpl (IProductRepository productRepository, ICartRepository cartRepository,ModelMapper modelMapper,ICartItemRepository cartItemRepository) {
            this.cartRepository = cartRepository;
            this.modelMapper = modelMapper;
            this.cartItemRepository = cartItemRepository;
            this.productRepository = productRepository;
        }


   @Override
    public CartDto getGuestCart(String sessionId) throws UsernameNotFound {

        Cart cart = cartRepository.findBySessionId(sessionId);

        // Crea el carrito si no existe
        if (cart == null) {
            Cart newCart = new Cart();
            newCart.setSessionId(sessionId);
            cartRepository.save(newCart);

            return modelMapper.map(newCart, CartDto.class);
        }

        return modelMapper.map(cart, CartDto.class);
    }

    @Override
    public CartDto addProductToGuestCart(String sessionId, AddItemRequestDto addItemRequestDto) throws NoElementException, ElementAlreadyExistsException {

        Cart cart = cartRepository.findBySessionId(sessionId);
       
        if(cart == null) {
            cart = new Cart();
            cart.setSessionId(sessionId);
            cartRepository.save(cart);
        }

        Product product = productRepository.findById(addItemRequestDto.getProductId())
                .orElseThrow(() -> new NoElementException("The product not exists"));

        Optional<CartItem> existingCartItem = cartItemRepository.findByProductIdAndCartId(product.getId(),
                cart.getId());

        if (existingCartItem.isPresent()) {
            throw new ElementAlreadyExistsException("The product is already in the cart.");
        }

        // Crea el CartItem
        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .product(product)
                .quantity(addItemRequestDto.getQuantity())
                .build();

        cartItem.setTotalAmount(cartItem.getTotalAmount());
        cartItemRepository.save(cartItem);

        cart.getItems().add(cartItem);
        cart.setQuantity(cart.getQuantity() + cartItem.getQuantity());
        cart.setTotalAmount(cart.getTotalAmount() + cartItem.getTotalAmount());

        cartRepository.save(cart);

        return modelMapper.map(cart, CartDto.class);

    }

    @Override
    public CartDto removeProductOfGuestCart(String sessionId, DeleteItemRequestDto deleteItemRequestDto) throws NoElementException {
              // Buscar el carrito del usuario
        Cart cart = cartRepository.findBySessionId(sessionId);

        if (cart == null) {
            throw new NoElementException("No cart found for session ID: " + sessionId);
        }

        // Buscar el ítem del carrito por el ID del producto
        CartItem cartItem = cartItemRepository
                .findByProductIdAndCartId(deleteItemRequestDto.getProductId(), cart.getId())
                .orElseThrow(() -> new NoElementException("The item not exists in the cart"));

        // Actualizar la cantidad total y el monto total del carrito
        cart.setQuantity(cart.getQuantity() - cartItem.getQuantity());
        cart.setTotalAmount(cart.getTotalAmount() - cartItem.getTotalAmount());

        // Eliminar el ítem del carrito
        cart.getItems().removeIf(item -> item.getProduct().getId().equals(deleteItemRequestDto.getProductId()));

        // Eliminar el ítem del repositorio
        cartItemRepository.deleteById(cartItem.getId());

        // Guardar los cambios en el carrito
        cartRepository.save(cart);

        // Mapear y devolver el carrito actualizado como DTO
        return modelMapper.map(cart, CartDto.class);
    }

    @Override
    public CartDto updateProductFromGuestCart(String sessionId, UpdateItemRequestDto updateItemRequestDto) throws NoElementException {
        
        Cart cart = cartRepository.findBySessionId(sessionId);
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

    @Override
    public CartDto mergeGuestCartToUser(String sessionId, Long userId) {

        Cart guestCart = cartRepository.findBySessionId(sessionId);
        Cart userCart = cartRepository.findByuserId(userId);

        if (guestCart != null) {

            Map<Long, CartItem> userCartItemsMap = userCart.getItems()
                    .stream()
                    .collect(Collectors.toMap(cartItem -> cartItem.getProduct().getId(), cartItem -> cartItem));

            for (CartItem guestItem : guestCart.getItems()) {

                Long productId = guestItem.getProduct().getId();

                if (userCartItemsMap.containsKey(productId)) {
                    CartItem userItem = userCartItemsMap.get(productId);

                    userItem.setQuantity(userItem.getQuantity() + guestItem.getQuantity());
                    userItem.setTotalAmount(userItem.getTotalAmount() + guestItem.getTotalAmount());
                } else {
                    CartItem newItem = new CartItem();
                    newItem.setProduct(guestItem.getProduct());
                    newItem.setQuantity(guestItem.getQuantity());
                    newItem.setTotalAmount(guestItem.getTotalAmount());
                    newItem.setCart(userCart);
                    userCart.getItems().add(newItem);
                }
            }
            userCart.setTotalAmount(userCart.getTotalAmount() + (guestCart.getTotalAmount()));
        }

        cartRepository.save(userCart);
        cartRepository.delete(guestCart);

        return modelMapper.map(userCart, CartDto.class);
    }

                    




    
}
