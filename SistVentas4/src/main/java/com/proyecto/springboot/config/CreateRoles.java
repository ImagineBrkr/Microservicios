package com.proyecto.springboot.config;

import com.proyecto.springboot.model.Rol;
import com.proyecto.springboot.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;



@Service
public class CreateRoles implements CommandLineRunner {
    @Autowired
    private RolRepository rolRepository;

    @Override
    public void run(String... args) throws Exception {

        Rol role = rolRepository.findByNombre("ROLE_ADMIN");
        if (role == null) {
            Rol rolAdmin = new Rol("ROLE_ADMIN");
            rolRepository.save(rolAdmin);
        }

        role = rolRepository.findByNombre("ROLE_VENDEDOR");
        if (role == null) {
            Rol rolUser = new Rol("ROLE_VENDEDOR");
            rolRepository.save(rolUser);
        }

    }
}