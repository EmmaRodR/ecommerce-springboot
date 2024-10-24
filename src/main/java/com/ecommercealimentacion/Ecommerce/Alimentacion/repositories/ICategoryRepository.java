package com.ecommercealimentacion.Ecommerce.Alimentacion.repositories;

import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<Category,Long> {


    @Query(value = "SELECT * FROM categories WHERE LOWER(name) = LOWER(:name)", nativeQuery = true)
    Optional<Category> findByName(String name);

    void deleteById(Long id);

    Page<Category> findAll (Pageable page);


}
