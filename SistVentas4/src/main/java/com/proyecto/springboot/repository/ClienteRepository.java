package com.proyecto.springboot.repository;

import com.proyecto.springboot.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    public Cliente findByRUC(String nombre);
    public Cliente findByid(Long id);
    boolean existsByRUC(Cliente cliente);
    long deleteByid(Long id);

}
