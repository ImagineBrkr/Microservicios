package com.productos.servicio;

import java.util.List;

import com.productos.modelo.Producto;

public interface ProductoService {

    public void save(Producto producto);

    public List<Producto> listarProductos();

    public boolean existsByNombre(String nombre);

    public Producto existsById(Long id);
    
    public void delete(long id);
    
    public Producto findById(long id);

}
