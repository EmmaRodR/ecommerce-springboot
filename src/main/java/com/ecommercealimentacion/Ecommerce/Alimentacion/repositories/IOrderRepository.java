package com.ecommercealimentacion.Ecommerce.Alimentacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.Order;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.User;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.enums.OrderStatus;

public interface IOrderRepository extends JpaRepository<Order,Long> {


    @Query(value="SELECT * FROM orders WHERE user_id=:id ORDER BY id DESC LIMIT 1",nativeQuery=true)
    Order findByUserId(Long id);

    java.util.Optional<Order> findByUserAndStatus(User user, OrderStatus waiting);
    
}
