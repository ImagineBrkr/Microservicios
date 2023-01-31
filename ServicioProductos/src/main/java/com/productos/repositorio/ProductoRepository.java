package com.productos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productos.modelo.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
    public Producto findByNombre(String nombre);
    boolean existsByNombre(Producto producto);
    
    public Producto findById(long id);
}
