package com.proyecto.springboot.repository;

import com.proyecto.springboot.model.OrdenDetalle;
import com.proyecto.springboot.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdenDetalleRepository extends JpaRepository<OrdenDetalle,Integer> {

    public Optional<OrdenDetalle> findById(Integer id);
    public List<OrdenDetalle> findByOrden(Orden orden);
    boolean existsById(OrdenDetalle ordenDetalle);

}
