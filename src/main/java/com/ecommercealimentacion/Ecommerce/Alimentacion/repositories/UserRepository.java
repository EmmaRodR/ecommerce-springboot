package com.ecommercealimentacion.Ecommerce.Alimentacion.repositories;

import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByUsername(String username);

    Optional<User> findByusername(String username);

    Boolean existsByUsername(String username);


}
