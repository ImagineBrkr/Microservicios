package com.proyecto.springboot.service;

import com.proyecto.springboot.model.Rol;
import com.proyecto.springboot.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    RolRepository rolRepository;

    public void save(Rol rol){
        rolRepository.save(rol);
    }

    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }

    public boolean existsByNombre(String nombre){
        Rol rol = rolRepository.findByNombre(nombre);
        if(rol == null){
            return false;
        }else{
            return true;
        }
    }

}
