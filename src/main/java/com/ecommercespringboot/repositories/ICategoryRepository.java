package com.ecommercespringboot.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommercespringboot.models.entities.Category;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {


    @Query(value = "SELECT * FROM categories WHERE LOWER(name) = LOWER(:name)", nativeQuery = true)
    Optional<Category> findByName(String name);

    void deleteById(Long id);

    Page<Category> findAll (Pageable page);


}
