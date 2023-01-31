package com.proyecto.springboot.dto;

import com.proyecto.springboot.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

public class OrdenDTO {

    private Long id;

    private String numero;

    private Date fecha;

    private  double total;

    private Usuario usuario;


}
