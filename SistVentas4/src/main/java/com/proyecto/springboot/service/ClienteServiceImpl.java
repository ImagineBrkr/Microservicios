package com.proyecto.springboot.service;

import com.proyecto.springboot.model.Cliente;
import com.proyecto.springboot.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServiceImpl implements  ClienteService{

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void save(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> listarClientes() {

        List<Cliente> clientes = clienteRepository.findAll();
        List<Cliente> clientesEstan = new ArrayList<Cliente>();
        for (int i=0;i<clientes.size();i++) {
            if (!clientes.get(i).isDeleted()) {
                clientesEstan.add(clientes.get(i));
            }
            
          }
        return clientesEstan;
    }

    @Override
    public boolean existsByRUC(String RUC) {
        Cliente cliente = clienteRepository.findByRUC(RUC);
        if(cliente == null){
            return false;
        }else{
            return true;
        }
    }
    
    @Override
    public void delete(Long id) {
        Cliente cliente = clienteRepository.findByid(id);
        cliente.setDeleted(Boolean.TRUE);
        this.save(cliente);
    }
    
    @Override
    public Cliente findByid(Long id) {
        return clienteRepository.findByid(id);
    }
}
