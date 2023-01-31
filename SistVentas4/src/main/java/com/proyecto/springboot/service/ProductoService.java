package com.proyecto.springboot.service;

import com.proyecto.springboot.dto.UsuarioDTO;
import com.proyecto.springboot.feignclient.GatewayClient;
import com.proyecto.springboot.model.Producto;
import com.proyecto.springboot.model.Usuario;

import feign.FeignException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductoService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    GatewayClient gatewayClient;
    @Autowired
    HttpSession httpSession;
 
    
    public String getToken() {
        String token;
        try {
            token = httpSession.getAttribute("token").toString();
        } catch(NullPointerException e) {
            token = "";
        }
        
        return "Bearer " + token;
    }

    public String save(Producto producto) {
        String result;
        try {
             result =gatewayClient.save(getToken(), producto);
        } catch(FeignException  e) {
            return e.getMessage();
        }
        return result;
    }
    
    public void edit(Producto producto) {
        gatewayClient.edit(getToken(), producto);
    }

    public List<Producto> listarProductos() {
        return gatewayClient.getAll(getToken());
    }

    public boolean existsByNombre(String nombre) {
        return true;
    }

    public Producto existsById(Long id) {
        return new Producto();
    }
    
    public Producto findByid(Long id) {
        return gatewayClient.get(getToken(), id);
    }
    
    public void delete(Long id) {
        gatewayClient.eliminar(getToken(), id);
    }

}
