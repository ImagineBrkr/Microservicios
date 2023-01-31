package com.proyecto.springboot.repository;

import com.proyecto.springboot.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Usuario findByEmail(String email);
    
    public Usuario findByid(long id);
}
