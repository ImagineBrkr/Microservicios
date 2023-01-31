package com.proyecto.springboot.controller;

import com.proyecto.springboot.model.Cliente;
import com.proyecto.springboot.model.Orden;
import com.proyecto.springboot.model.OrdenDetalle;
import com.proyecto.springboot.model.OrdenProductoAux;
import com.proyecto.springboot.model.Producto;
import com.proyecto.springboot.model.Usuario;
import com.proyecto.springboot.service.OrdenDetalleService;
import com.proyecto.springboot.service.OrdenService;
import com.proyecto.springboot.service.ClienteService;

import com.proyecto.springboot.service.ProductoService;
import com.proyecto.springboot.service.UsuarioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/orden")
public class OrdenController {

    @Autowired
    OrdenService ordenService;

    @Autowired
    ProductoService productoService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    OrdenDetalleService ordenDetalleService;
    
    @Autowired
    ClienteService clienteService;

    @GetMapping("/lista")
    public String listarOrden(Model modelo){
        modelo.addAttribute("titulo","Ventas");
        modelo.addAttribute("ordenes",ordenService.listarOrdenes());
        return "orden/lista";
    }
    
    @GetMapping("/nuevo")
    public String nuevaOrden(Model modelo){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario userDetails = null;
        if (principal instanceof Usuario) {
            userDetails = (Usuario) principal;
        }
        String userName = userDetails.getEmail();
        Usuario usuario = usuarioService.findByEmail(userName);
        String fecha = LocalDate.now().toString();
        modelo.addAttribute("titulo","Nueva Orden de Compra");
        modelo.addAttribute("fecha",fecha);
        modelo.addAttribute("usuario",userName);
        modelo.addAttribute("clientes",clienteService.listarClientes());
        modelo.addAttribute("productos",productoService.listarProductos());
        return "orden/nuevo";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable("id") long id, Model modelo){
        Orden orden = ordenService.getOrden(id);

        modelo.addAttribute("titulo","Detalle orden");
        modelo.addAttribute("detalles",ordenDetalleService.findByOrden(orden));
        return "orden/detalle";
    }
    

    @PostMapping("/guardar")
    public String guardarOrden(@RequestParam List<Long> producto ,@RequestParam List<Integer> cantidad, @RequestParam Long cliente_id,Model modelo){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        Usuario userDetails = null;
        if (principal instanceof Usuario) {
            userDetails = (Usuario) principal;
        }
        String userName = userDetails.getEmail();;
        Usuario usuario = usuarioService.findByEmail(userName);
        Cliente cliente;
        cliente = clienteService.findByid(cliente_id);

        Date fecha = new Date();
        double total =0;
        Orden orden = new Orden(fecha,total,usuario, cliente);

        for(int i = 0; i <= producto.size()-1; i +=1 ){
                Producto product = productoService.findByid(producto.get(i));
                if(cantidad.get(i) <= 0) {
                    modelo.addAttribute("error","La cantidad no puede ser 0" );
                    return nuevaOrden(modelo);
                }          
                if(product.getStock() >= cantidad.get(i)){
                    }else{
                        modelo.addAttribute("error","La cantidad solicitada del producto " + product.getNombre() + " es mayor al stock" );
                        return nuevaOrden(modelo);
                    }
                total += cantidad.get(i) * product.getPrecio();
        }
        
        orden.setTotal(total);
        ordenService.save(orden);
        for(int i = 0; i <= producto.size()-1; i +=1 ){
            if(productoService.findByid( producto.get(i)) != null){
                Producto product = productoService.findByid(producto.get(i));
                OrdenDetalle ordenDetalle = new OrdenDetalle(cantidad.get(i),producto.get(i),orden);
                ordenDetalleService.save(ordenDetalle);
                product.setStock(product.getStock()-cantidad.get(i));
                productoService.edit(product);

            }
        }       
        
       


        return "redirect:/orden/lista";
    }

    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id, Model modelo) {
        ordenService.delete(id);
        Producto producto;
        List<OrdenProductoAux> ordenDetalle = ordenDetalleService.findByOrden(ordenService.getOrden(id));
        for (int i=0;i<ordenDetalle.size();i++) {
            producto = productoService.findByid(ordenDetalle.get(i).getOrden().getProducto_id());
            producto.setStock(producto.getStock()+ordenDetalle.get(i).getOrden().getCantidad());
            productoService.edit(producto);            
          }
        return "redirect:/orden/lista";
    }



}
