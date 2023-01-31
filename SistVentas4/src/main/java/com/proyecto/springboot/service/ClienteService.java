package com.proyecto.springboot.service;

import com.proyecto.springboot.model.Cliente;

import java.util.List;

public interface ClienteService {

    public void save(Cliente cliente);

    public List<Cliente> listarClientes();

    public boolean existsByRUC(String RUC);
    
    public Cliente findByid(Long id);
        
    public void delete(Long id);
}
