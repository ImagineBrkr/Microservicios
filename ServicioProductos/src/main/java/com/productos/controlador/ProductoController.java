package com.productos.controlador;

import com.productos.modelo.Producto;
import com.productos.servicio.ProductoService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // @Autowired
    // private CategoriaService categoriaService;
    
    
    @GetMapping("/lista")
    public ResponseEntity<List<Producto>> getAll() {
        List<Producto> productos = productoService.listarProductos();
        if(productos.isEmpty())
           return ResponseEntity.noContent().build();
       return ResponseEntity.ok(productos);
    }
    
    @PostMapping("/guardar")
    public ResponseEntity<String> save(@RequestBody Producto producto) {
        if(StringUtils.isBlank(producto.getNombre())){
            return new ResponseEntity<String>("Nombre en blanco", HttpStatus.BAD_REQUEST);
        }

        if(producto.getPrecio() < 0) {
            return new ResponseEntity<String>("El precio no puede ser menor a 0", HttpStatus.BAD_REQUEST);
        }

        if(producto.getStock() < 0){
        	return new ResponseEntity<String>("El stock no puede ser menor a 0", HttpStatus.BAD_REQUEST);
        }


        if(productoService.existsByNombre(producto.getNombre())){
        	return new ResponseEntity<String>("El producto ya existe", HttpStatus.BAD_REQUEST);
        }
        producto.setDeleted(false);
        productoService.save(producto);
        return ResponseEntity.ok("Producto guardado");
    }
    
    @GetMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
        productoService.delete(id);
        return ResponseEntity.ok("Producto eliminado");
    }
    
    @PostMapping("/editar")
    public ResponseEntity<String> edit(@RequestBody Producto producto) {
        if(StringUtils.isBlank(producto.getNombre())){
            return new ResponseEntity<String>("Nombre en blanco", HttpStatus.BAD_REQUEST);
        }

        if(producto.getPrecio() < 0) {
            return new ResponseEntity<String>("El precio no puede ser menor a 0", HttpStatus.BAD_REQUEST);
        }

        if(producto.getStock() < 0){
        	return new ResponseEntity<String>("El stock no puede ser menor a 0", HttpStatus.BAD_REQUEST);
        }
        productoService.save(producto);
        return ResponseEntity.ok("Producto guardado");
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Producto> get(@PathVariable("id") Long id) {
        Producto producto = productoService.findById(id);
        if (producto == null) {
        	return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(producto);
    }


    public String listarProductos(Model modelo){
        modelo.addAttribute("titulo","Productos");
        modelo.addAttribute("productos",productoService.listarProductos());
        return "producto/lista";
    }
    
     public String nuevoProducto(Model modelo){
         modelo.addAttribute("titulo","Nuevo Producto");
        //  modelo.addAttribute("categorias",categoriaService.listarCategorias());
         return "producto/nuevo";
    }

    public String guardarProducto(@RequestParam String nombre, @RequestParam float precio,@RequestParam int stock, Model modelo){
        if(StringUtils.isBlank(nombre)){
            modelo.addAttribute("error","El nombre no puede ser vacio");
            return "producto/nuevo";
        }

        if(precio < 0) {
            modelo.addAttribute("error","El precio no puede ser menor a 0");
            return "producto/nuevo";
        }

        if(stock < 0){
            modelo.addAttribute("error","El stock no puede ser menor a 0");
            return "producto/nuevo";
        }


        if(productoService.existsByNombre(nombre)){
            modelo.addAttribute("error","Este producto '"+nombre+"' ya existe.");
            return "producto/nuevo";
        }

        // if(categoria.getId()<=0){
        //     modelo.addAttribute("error","Escoger una categoria valida");
        //     return "producto/nuevo";
        // }

        Producto producto = new Producto(nombre,precio,stock);
        productoService.save(producto);
        return "redirect:/producto/lista";
    }

    public String eliminar(@PathVariable("id") Long id, Model modelo) {
        productoService.delete(id);
        return "redirect:/producto/lista";
    }

}
