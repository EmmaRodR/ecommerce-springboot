package com.ecommercealimentacion.Ecommerce.Alimentacion.repositories;

import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, Long> {


    @Query(value = "SELECT * FROM products WHERE LOWER(name) = LOWER(:name)", nativeQuery = true)
    Optional<Product> findByName(String name);


    @Query(value = "SELECT * FROM products WHERE id = :id", nativeQuery = true)
    Optional<Product> findById(Long id);


    @Query(value = "SELECT * FROM products WHERE LOWER(name) LIKE CONCAT('%', LOWER(:name), '%')", nativeQuery = true)
    List<Product> findByNameFindCoincidence(String name);



    @Query(value = "SELECT * FROM products WHERE categorie_id = :id", nativeQuery = true)
    List<Product> findByCategory(Long id);

    @Query(value = "SELECT * FROM products ORDER BY id", nativeQuery = true)
    Page<Product> findAll (Pageable page);


}
