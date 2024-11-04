package com.ecommercespringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommercespringboot.models.entities.Order;
import com.ecommercespringboot.models.entities.User;
import com.ecommercespringboot.models.enums.OrderStatus;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {


    @Query(value="SELECT * FROM orders WHERE user_id=:id ORDER BY id DESC LIMIT 1",nativeQuery=true)
    Order findByUserId(Long id);

    java.util.Optional<Order> findByUserAndStatus(User user, OrderStatus waiting);
    
}
