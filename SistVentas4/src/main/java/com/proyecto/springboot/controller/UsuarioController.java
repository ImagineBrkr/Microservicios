package com.proyecto.springboot.controller;

import com.proyecto.springboot.model.Rol;
import com.proyecto.springboot.model.Usuario;
import com.proyecto.springboot.service.RolService;
import com.proyecto.springboot.service.RolServiceImpl;
import com.proyecto.springboot.service.UsuarioService;
import com.proyecto.springboot.dto.UsuarioRegistroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista")
    public String listUsuarios(Model modelo){
        modelo.addAttribute("titulo","Usuarios");
        modelo.addAttribute("usuarios",usuarioService.listarUsuarios());
        return "usuario/lista";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/nuevo")
    public String nuevo(Model modelo){
        modelo.addAttribute("titulo","Nuevo Usuario");
        modelo.addAttribute("roles",rolService.listarRoles());
        return "usuario/nuevo";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable("id") Long id, Model modelo){
        if(!usuarioService.existsById(id)){
            return "redirect: /lista";
        }

        Usuario usuario = usuarioService.getUsuario(id);
        modelo.addAttribute("titulo","Detalle Usuario");
        modelo.addAttribute("usuario",usuario);
        return "usuario/detalle";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/guardar")
    public String crearUsuario(@RequestParam String email, @RequestParam String password, @RequestParam String roles, Model modelo){
        if(StringUtils.isBlank(email)){
            modelo.addAttribute("error","El email no puede ser vacio");
            return nuevo(modelo);
        }
        if(StringUtils.isBlank(password)){
            modelo.addAttribute("error","El password no puede ser vacio");
            return nuevo(modelo);
        }

        if(usuarioService.existsByEmail(email)){
            modelo.addAttribute("error","Este email '"+email+"' ya existe.");
            return nuevo(modelo);
        }
        if(StringUtils.isBlank(roles)){
            modelo.addAttribute("error","El rol no puede ser vacio");
            return nuevo(modelo);
        }
        if(roles.equals("1")) {
            UsuarioRegistroDTO usuario = new UsuarioRegistroDTO(email, password);
            usuarioService.save(usuario);
        } else {
            Usuario usuario = new Usuario(email,password);
            usuarioService.save(usuario);
        }        
        
        return "redirect:/usuario/lista";
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") long id, Model modelo) {
        usuarioService.delete(id);
        return "redirect:/usuario/lista";
    }


}
