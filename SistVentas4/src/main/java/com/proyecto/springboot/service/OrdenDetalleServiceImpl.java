package com.proyecto.springboot.service;

import com.proyecto.springboot.model.Orden;
import com.proyecto.springboot.model.OrdenDetalle;
import com.proyecto.springboot.model.OrdenProductoAux;
import com.proyecto.springboot.model.Producto;
import com.proyecto.springboot.repository.OrdenDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenDetalleServiceImpl implements OrdenDetalleService{

    @Autowired
    OrdenDetalleRepository ordenDetalleRepository;
    @Autowired
    ProductoService productoService;

    @Override
    public void save(OrdenDetalle ordenDetalle) {
        ordenDetalleRepository.save(ordenDetalle);
    }

    @Override
    public List<OrdenDetalle> listarOrdenDetalles() {
        return ordenDetalleRepository.findAll();
    }
    
    @Override
    public List<OrdenProductoAux> findByOrden(Orden orden) {
        List<OrdenDetalle> detalles = ordenDetalleRepository.findByOrden(orden);
        Producto producto;
        List<OrdenProductoAux> lista = new ArrayList<OrdenProductoAux>();
        for (int i=0;i<detalles.size();i++) {
            producto = productoService.findByid(detalles.get(i).getProducto_id());
            lista.add(new OrdenProductoAux(producto, detalles.get(i)));            
          }
       return lista;
    }

}
