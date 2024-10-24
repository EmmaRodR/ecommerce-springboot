package com.ecommercealimentacion.Ecommerce.Alimentacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.OrderItem;

@Repository
public interface IOrderItemRepository extends JpaRepository<OrderItem,Long>  {
    
}
