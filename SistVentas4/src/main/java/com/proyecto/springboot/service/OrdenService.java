package com.proyecto.springboot.service;

import com.proyecto.springboot.model.Orden;
import com.proyecto.springboot.model.Producto;
import com.proyecto.springboot.model.Usuario;

import org.aspectj.weaver.ast.Or;

import java.util.List;
import java.util.Optional;

public interface OrdenService {

    public void save(Orden orden);

    public List<Orden> listarOrdenes();

    public boolean existsById(Long id);

    public Orden getOrden(Long id);

    public void delete(Long id);

}
