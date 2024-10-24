package com.ecommercealimentacion.Ecommerce.Alimentacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.Cart;

@Repository
public interface ICartRepository extends JpaRepository<Cart,Long> {

    
    Cart findByuserId(Long userId);
    Cart findBySessionId(String sessionId);





}
    

