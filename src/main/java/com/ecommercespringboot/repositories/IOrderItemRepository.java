package com.ecommercespringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercespringboot.models.entities.OrderItem;

@Repository
public interface IOrderItemRepository extends JpaRepository<OrderItem,Long>  {
    
}
