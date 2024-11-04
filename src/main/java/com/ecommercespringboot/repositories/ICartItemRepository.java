package com.ecommercespringboot.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommercespringboot.models.entities.CartItem;

@Repository
public interface ICartItemRepository extends JpaRepository<CartItem,Long> {

    @Query(value ="SELECT * FROM cart_items WHERE product_id =:productId",nativeQuery = true)
    Optional<CartItem> findByProductId(Long productId);

    Optional<CartItem> findByProductIdAndCartId(Long productId,Long cart);
    
}
