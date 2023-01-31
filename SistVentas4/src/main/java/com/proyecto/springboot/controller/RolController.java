package com.proyecto.springboot.controller;

import com.proyecto.springboot.model.Rol;
import com.proyecto.springboot.service.RolServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private RolServiceImpl rolService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista")
    public String listUsuarios(Model modelo){
        modelo.addAttribute("titulo","Roles");
        modelo.addAttribute("roles",rolService.listarRoles());
        return "roles/lista";
    }


    @GetMapping("/nuevo")
    public String nuevoRol(Model modelo){
        modelo.addAttribute("titulo","Nuevo Rol");
        return "roles/nuevo";
    }

    @PostMapping("/guardar")
    public String guardarRol(@RequestParam String nombre, Model modelo){
        if(StringUtils.isBlank(nombre)){
            modelo.addAttribute("error","El nombre no puede ser vacio");
            return "roles/nuevo";
        }

        if(rolService.existsByNombre(nombre)){
            modelo.addAttribute("error","Este rol '"+nombre+"' ya existe.");
            return "roles/nuevo";
        }

        Rol rol = new Rol(nombre);
        rolService.save(rol);
        return "redirect:/rol/lista";
    }

}
