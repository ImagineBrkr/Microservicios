package com.proyecto.springboot.controller;

import com.proyecto.springboot.model.Usuario;
import com.proyecto.springboot.dto.*;
import com.proyecto.springboot.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

//    @GetMapping("/")
//    public String index(){
//        if (!usuarioService.existsByEmail("admin@admin.com")){
//            Usuario user = new Usuario("Admin", "Admin", "admin@admin.com", "123456");
//            usuarioService.save(user);
//        }
//        return "login";
//    }

    @GetMapping("/login")
    public String iniciarSesion(){
        if (!usuarioService.existsByEmail("admin@admin.com")){
            UsuarioRegistroDTO user = new UsuarioRegistroDTO("Admin", "Admin", "admin@admin.com", "123456");
            usuarioService.save(user);
        }
        return "login"; }

    @GetMapping(value = {"/", "/index"})
    public String verPaginaInicio(Model model){
        model.addAttribute("titulo","Inicio");
        return "index";
    }


}
