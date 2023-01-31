package com.proyecto.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrdenProductoAux {

    private Producto producto;
    private OrdenDetalle orden;  
    
    public OrdenProductoAux(Producto producto, OrdenDetalle orden) {
        super();
        this.producto = producto;
        this.orden = orden;
    }
    
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public OrdenDetalle getOrden() {
        return orden;
    }
    public void setOrden(OrdenDetalle orden) {
        this.orden = orden;
    }
    
    
}
