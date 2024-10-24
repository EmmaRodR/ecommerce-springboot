package com.ecommercealimentacion.Ecommerce.Alimentacion.utils.mappers;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.orders.OrderDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.products.ProductOrderInfoDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.Order;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.OrderItem;

@Service
public class OrderDtoMapper {

    public OrderDto convertToDto (Order order) {

        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUserName(order.getUser().getUsername());
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setStatus(order.getStatus());

        List<ProductOrderInfoDto> products = order.getOrderItems()
                                .stream()
                                .map(this::convertOrderItemToProductOrderInfoDto)
                                .collect(Collectors.toList());
        
        orderDto.setProducts(products);
        return orderDto;

    }

    public ProductOrderInfoDto convertOrderItemToProductOrderInfoDto(OrderItem orderItem) {
        ProductOrderInfoDto productOrderInfoDto = new ProductOrderInfoDto();
        productOrderInfoDto.setProductName(orderItem.getProduct().getName());
        productOrderInfoDto.setQuantity(orderItem.getQuantity());
        productOrderInfoDto.setTotalAmount(orderItem.getProduct().getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())));
        return productOrderInfoDto;
    }


    
}
