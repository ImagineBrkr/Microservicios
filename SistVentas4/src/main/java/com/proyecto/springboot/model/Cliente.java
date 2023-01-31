package com.proyecto.springboot.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    @Column(length =11, nullable = false,unique = true)
    private String RUC;

    @Getter @Setter
    @Column(length =60, nullable = false)
    private String RazonSocial;

    @Getter @Setter
    private boolean deleted = Boolean.FALSE;


    public Cliente(){

    }

    public Cliente(String RUC) {
        this.RUC = RUC;
    }
    public Cliente(String RUC, String razonSocial) {
        this.RUC = RUC;
        this.RazonSocial = razonSocial;
    }
    public Cliente(Long id, String RUC, String razonSocial) {
        this.id = id;
        this.RUC = RUC;
        this.RazonSocial = razonSocial;
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

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        RazonSocial = razonSocial;
    }
    @Override
    public String toString() {
        return  RUC + " " + RazonSocial;
    }
}
