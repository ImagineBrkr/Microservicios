package com.proyecto.springboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orden")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
     @Getter @Setter
    private Date fecha;
    @Getter @Setter
    private  double total;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @Getter @Setter
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @Getter @Setter
    private Cliente cliente;
    
    @Getter @Setter
    private boolean deleted = Boolean.FALSE;


    public Orden() {
    }

    public Orden(Long id, Date fecha, double total, Usuario usuario, Cliente cliente) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.usuario = usuario;
        this.cliente = cliente;
    }

    public Orden(Date fecha, double total, Usuario usuario, Cliente cliente) {
        this.fecha = fecha;
        this.total = total;
        this.usuario = usuario;
        this.cliente = cliente;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Orden{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", total=" + total +
                ", usuario=" + usuario +
                ", cliente=" + cliente +
                '}';
    }
}
