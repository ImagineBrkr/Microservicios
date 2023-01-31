package com.tutorial.authservice.repository;

import com.tutorial.authservice.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
