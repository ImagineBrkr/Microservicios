package com.proyecto.springboot.dto;

import lombok.Getter;
import lombok.Setter;


public class ProductoDTO {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String nombre;

    @Getter @Setter
    private float precio;

    @Getter @Setter
    private int stock;

    // @Getter @Setter
    // private Categoria categoria;

    public ProductoDTO() {
    }

    // public ProductoDTO(String nombre, float precio, int stock, Categoria categoria) {
    //     this.nombre = nombre;
    //     this.precio = precio;
    //     this.stock = stock;
    //     this.categoria = categoria;
    // }

    public ProductoDTO(String nombre, float precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;

    }

    // public ProductoDTO(Long id, String nombre, float precio, int stock, Categoria categoria) {
    //     this.id = id;
    //     this.nombre = nombre;
    //     this.precio = precio;
    //     this.stock = stock;
    //     this.categoria = categoria;
    // }
}
