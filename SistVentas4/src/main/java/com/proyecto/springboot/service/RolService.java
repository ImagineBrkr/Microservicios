package com.proyecto.springboot.service;

import com.proyecto.springboot.model.Rol;

import java.util.List;

public interface RolService {

    public void save(Rol rol);

    public List<Rol> listarRoles();

    public boolean existsByNombre(String nombre);
}
