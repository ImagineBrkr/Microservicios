package com.proyecto.springboot.repository;

import com.proyecto.springboot.model.Rol;
import com.proyecto.springboot.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol,Long> {

    public Rol findByNombre(String nombre);
    boolean existsByNombre(Rol rol);

}
