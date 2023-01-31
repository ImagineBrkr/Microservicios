package com.proyecto.springboot.service;

import com.proyecto.springboot.model.Orden;
import com.proyecto.springboot.model.OrdenDetalle;
import com.proyecto.springboot.model.OrdenProductoAux;

import java.util.List;

public interface OrdenDetalleService {

    public void save(OrdenDetalle ordenDetalle);

    public List<OrdenDetalle> listarOrdenDetalles();
    
    public List<OrdenProductoAux> findByOrden(Orden orden);


}
