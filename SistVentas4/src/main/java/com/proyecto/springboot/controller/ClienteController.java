package com.proyecto.springboot.controller;

import com.proyecto.springboot.model.Cliente;
import com.proyecto.springboot.service.ClienteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService ClienteService;

    @GetMapping("/lista")
    public String listarClientes(Model modelo){
        modelo.addAttribute("titulo","Clientes");
        modelo.addAttribute("clientes",ClienteService.listarClientes());
        return "cliente/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoCliente(Model modelo){
        modelo.addAttribute("titulo","Nuevo Cliente");
        return "cliente/nuevo";
    }


    @PostMapping("/guardar")
    public String guardarCliente(@RequestParam String RUC, @RequestParam String razonsocial, Model modelo){
        if(StringUtils.isBlank(RUC) || StringUtils.isBlank(razonsocial)){
            modelo.addAttribute("error","Los campos no pueden estar vacios");
            return "cliente/nuevo";
        }

        if(ClienteService.existsByRUC(RUC)){
            modelo.addAttribute("error","El cliente con este RUC : '"+RUC+"' ya existe.");
            return "cliente/nuevo";
        }
        
        if(!(RUC.length() == 11)) {
            modelo.addAttribute("error","El RUC debe tener 11 caracteres.");
            return "cliente/nuevo";           
        }

        Cliente Cliente = new Cliente(RUC, razonsocial);
        ClienteService.save(Cliente);
        return "redirect:/cliente/lista";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable("id") Long id, Model modelo) {
        ClienteService.delete(id);
        return "redirect:/cliente/lista";
    }



}
