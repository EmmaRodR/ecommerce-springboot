package com.ecommercespringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommercespringboot.models.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByUsername(String username);

    Optional<User> findByusername(String username);

    Boolean existsByUsername(String username);


}
