package com.ecommercespringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercespringboot.models.entities.Cart;

@Repository
public interface ICartRepository extends JpaRepository<Cart,Long> {

    
    Cart findByuserId(Long userId);
    Cart findBySessionId(String sessionId);





}
    

