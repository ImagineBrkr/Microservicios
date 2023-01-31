package com.proyecto.springboot.controller;

import com.proyecto.springboot.model.Producto;

import com.proyecto.springboot.service.ProductoService;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired(required = true)
    private ProductoService productoService;
    @Autowired
    RestTemplate restTemplate;
    
    //@Resource(name = "sessionToken")
    //TokenDTO sessionToken;
    // @Autowired
    // private CategoriaService categoriaService;

    @GetMapping("/lista")
    public String listarProductos(Model modelo, HttpSession session){
        modelo.addAttribute("titulo","Productos");
        modelo.addAttribute("productos",productoService.listarProductos());
        return "producto/lista";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
     @GetMapping("/nuevo")
     public String nuevoProducto(Model modelo){
         modelo.addAttribute("titulo","Nuevo Producto");
        //  modelo.addAttribute("categorias",categoriaService.listarCategorias());
         return "producto/nuevo";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public String guardarProducto(@RequestParam String nombre, @RequestParam float precio,@RequestParam int stock, Model modelo){
        Producto producto = new Producto(nombre,precio,stock);
        String respuesta = productoService.save(producto);
        if (respuesta.equals("Producto guardado")) {
            return "redirect:/producto/lista";
        } else {
            modelo.addAttribute("error",respuesta);
            return "producto/nuevo";
        }     
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id, Model modelo) {
        productoService.delete(id);
        return "redirect:/producto/lista";
    }

}
