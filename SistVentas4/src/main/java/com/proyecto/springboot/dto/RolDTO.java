package com.proyecto.springboot.dto;
import lombok.Getter;
import lombok.Setter;

public class RolDTO {

    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String nombre;

    public RolDTO() {
    }

    public RolDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public RolDTO(String nombre) {
        this.nombre = nombre;
    }
}
