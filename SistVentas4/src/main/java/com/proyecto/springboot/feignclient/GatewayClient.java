package com.proyecto.springboot.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.springboot.dto.UsuarioDTO;
import com.proyecto.springboot.model.Producto;

@FeignClient(name="gatewayservice")
public interface GatewayClient {
    @GetMapping("/producto/lista")
    public List<Producto> getAll(@RequestHeader(value = "Authorization", required = true) String authorizationHeader);
    @PostMapping("/producto/guardar")
    public String save(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,@RequestBody Producto producto);
    @GetMapping("/producto/eliminar/{id}")
    public String eliminar(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,@PathVariable("id") Long id);
    @PostMapping("/producto/editar")
    public String edit(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,@RequestBody Producto producto);
    @GetMapping("/producto/{id}")
    public Producto get(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,@PathVariable("id") Long id);
    @GetMapping("/auth/login")
    public String login(@RequestBody UsuarioDTO usuario);
}
