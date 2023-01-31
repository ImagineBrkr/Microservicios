package com.productos.modelo;


import javax.persistence.*;

@Entity
@Table(name ="producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, nullable = false ,unique = true)
    private String nombre;

    private float precio;

    private int stock;
    
    private boolean deleted = Boolean.FALSE;

    // @ManyToOne
    // @JoinColumn(name = "categoria_id")
    // @Getter @Setter
    // private Categoria categoria;

    public Producto() {
    }

    public Producto(Long id, String nombre, float precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        // this.categoria = categoria;
    }

    public Producto(String nombre, float precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        // this.categoria = categoria;
    }
    
    

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // public Categoria getCategoria() {
    //     return categoria;
    // }

    // public void setCategoria(Categoria categoria) {
    //     this.categoria = categoria;
    // }

    public Producto(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return  nombre;
    }
}
