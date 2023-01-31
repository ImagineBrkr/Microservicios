package com.proyecto.springboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "orden_detalle")
public class OrdenDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private int cantidad;

    @Getter @Setter
    private long producto_id;

    @ManyToOne
    @JoinColumn(name = "orden_id" )
    @Getter @Setter
    private Orden orden;

    public OrdenDetalle() {
    }

    public OrdenDetalle(Integer id, int cantidad, long producto, Orden orden) {
        this.id = id;
        this.cantidad = cantidad;
        this.producto_id = producto;
        this.orden = orden;
    }

    public OrdenDetalle(int cantidad, long producto, Orden orden) {
        this.id = id;
        this.cantidad = cantidad;
        this.producto_id = producto;
        this.orden = orden;
    }


    public OrdenDetalle(long producto, Orden orden) {
        this.producto_id = producto;
        this.orden = orden;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public long getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(long producto) {
        this.producto_id = producto;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }
    
    


}
