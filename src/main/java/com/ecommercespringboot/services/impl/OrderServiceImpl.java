package com.ecommercespringboot.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommercespringboot.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercespringboot.exceptions.especificExceptions.NoElementException;
import com.ecommercespringboot.exceptions.especificExceptions.UsernameNotFound;
import com.ecommercespringboot.models.dtos.orders.OrderDto;
import com.ecommercespringboot.models.entities.Cart;
import com.ecommercespringboot.models.entities.CartItem;
import com.ecommercespringboot.models.entities.Order;
import com.ecommercespringboot.models.entities.OrderItem;
import com.ecommercespringboot.models.entities.User;
import com.ecommercespringboot.models.enums.OrderStatus;
import com.ecommercespringboot.repositories.ICartRepository;
import com.ecommercespringboot.repositories.IOrderItemRepository;
import com.ecommercespringboot.repositories.IOrderRepository;
import com.ecommercespringboot.repositories.UserRepository;
import com.ecommercespringboot.services.IOrderService;
import com.ecommercespringboot.utils.mappers.OrderDtoMapper;

@Service
public class OrderServiceImpl implements IOrderService {

    private ICartRepository cartRepository;

    private IOrderItemRepository orderItemRepository;

    private UserRepository userRepository;

    private IOrderRepository orderRepository;

    private OrderDtoMapper orderDtoMapper;

    public OrderServiceImpl(
            ICartRepository cartRepository,
            UserRepository userRepository,
            IOrderRepository orderRepository,
            IOrderItemRepository orderItemRepository,
            OrderDtoMapper orderDtoMapper) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderDtoMapper = orderDtoMapper;
    }

    @Override
    public OrderDto createOrder(Long userId) throws UsernameNotFound,ElementAlreadyExistsException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFound("Not found a user with the id: " + userId));

        Cart cart = cartRepository.findByuserId(userId);
        if (cart == null ||cart.getItems().isEmpty()) {
            throw new IllegalStateException("The cart is empty or does not exists for the user");
        }

        Optional<Order> existingOrder = orderRepository.findByUserAndStatus(user,OrderStatus.WAITING);

        if (existingOrder.isPresent()) {
            throw new ElementAlreadyExistsException("An waiting order is already in progress for user");
        }

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.WAITING);

        BigDecimal totalAmount = new BigDecimal(cart.getTotalAmount());
        order.setTotalAmount(totalAmount);
        
        orderRepository.save(order);

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }

        orderItemRepository.saveAll(orderItems);
        order.setOrderItems(orderItems);
        orderRepository.save(order);

        cart.getItems().clear(); 
        cartRepository.save(cart);

        return orderDtoMapper.convertToDto(order);
    }

    @Override
    public OrderDto getOrderByUserId(Long userId) throws UsernameNotFound, NoElementException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFound("Not found a user with the id: " + userId));

        Order order = orderRepository.findByUserId(user.getId());
        Order newOrder = new Order();

        if (order == null) {
            newOrder = new Order();
            newOrder.setUser(user);
            newOrder.setOrderItems(new ArrayList<>());
            newOrder.setTotalAmount(null);
            newOrder.setStatus(OrderStatus.CREATED);
        } 

         return orderDtoMapper.convertToDto(order != null ? order : newOrder);
        
    }

    @Override
    public OrderDto updateOrder(Long userId, int quantity) throws UsernameNotFound {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOrder'");
    }

    @Override
    public ResponseEntity<?> deleteOrder (Long userId) throws UsernameNotFound {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFound("Not found a user with the id: " + userId));

        Order order = orderRepository.findByUserId(user.getId());

        orderRepository.delete(order);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
