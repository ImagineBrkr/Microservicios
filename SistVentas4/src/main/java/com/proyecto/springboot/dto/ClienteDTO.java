package com.proyecto.springboot.dto;

import lombok.Getter;
import lombok.Setter;

public class ClienteDTO {

    @Getter
    @Setter
    private Long id;

    @Getter @Setter
    private String RUC;

    @Getter @Setter
    private String RazonSocial;

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String RUC, String razonSocial) {
        this.id = id;
        this.RUC = RUC;
        RazonSocial = razonSocial;
    }
}
