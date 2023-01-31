package com.productos.servicio;

import com.productos.modelo.Producto;
import com.productos.repositorio.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements  ProductoService{

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public List<Producto> listarProductos() {
        List<Producto> listaTodos = productoRepository.findAll();
        List<Producto> lista = new ArrayList<Producto>();
        for (int i=0;i<listaTodos.size();i++) {
            if (!listaTodos.get(i).isDeleted()) {
                lista.add(listaTodos.get(i));
            }
            
          }
        return lista;
    }

    @Override
    public boolean existsByNombre(String nombre) {
        Producto producto = productoRepository.findByNombre(nombre);
        if(producto == null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Producto existsById(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if(producto == null){
            return null;
        }else{
            return producto.get();
        }
    }
    
    @Override
    public Producto findById(long id) {
        return productoRepository.findById(id);
    }
    
    @Override
    public void delete(long id) {
        Producto producto = productoRepository.findById(id);
        producto.setDeleted(Boolean.TRUE);
        this.save(producto);
    }
}

