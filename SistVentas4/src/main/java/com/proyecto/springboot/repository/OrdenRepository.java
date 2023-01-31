package com.proyecto.springboot.repository;

import com.proyecto.springboot.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden,Long> {

    public Orden findByid(Long id);
    boolean existsById(Orden orden);

}
