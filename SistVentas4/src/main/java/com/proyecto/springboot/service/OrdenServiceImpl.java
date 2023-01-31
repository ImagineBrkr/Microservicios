package com.proyecto.springboot.service;

import com.proyecto.springboot.model.Cliente;
import com.proyecto.springboot.model.Orden;
import com.proyecto.springboot.model.OrdenDetalle;
import com.proyecto.springboot.model.Producto;
import com.proyecto.springboot.model.Usuario;
import com.proyecto.springboot.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenServiceImpl  implements OrdenService{

    @Autowired
    OrdenRepository ordenRepository;
    @Autowired
    OrdenDetalleService ordenDetalleService;
    @Autowired
    ProductoService productoService;

    @Override
    public void save(Orden orden) {
        ordenRepository.save(orden);
    }

    @Override
    public List<Orden> listarOrdenes() {
        List<Orden> listaTodos = ordenRepository.findAll();
        List<Orden> lista = new ArrayList<Orden>();
        for (int i=0;i<listaTodos.size();i++) {
            if (!listaTodos.get(i).isDeleted()) {
                lista.add(listaTodos.get(i));
            }
            
          }
        return lista;
    }

    @Override
    public boolean existsById(Long id) {
        Orden orden = ordenRepository.findByid(id);
        if(orden == null){
            return false;
        }else{
            return true;
        }
    }
    
    @Override
    public Orden getOrden(Long id) {
        return ordenRepository.findByid(id);
    }

    @Override
    public void delete(Long id) {
        Orden orden = ordenRepository.findByid(id);
        orden.setDeleted(Boolean.TRUE);        
        this.save(orden);
        //List<OrdenDetalle> detalle = ordenDetalleService.findByOrden(orden);
        //for (int i=0;i<detalle.size();i++) {
            //Producto producto = detalle.get(i).getProducto();
            //producto.setStock(producto.getStock() + detalle.get(i).getCantidad());
            //productoService.save(producto);            
          //}
    }
    
}
