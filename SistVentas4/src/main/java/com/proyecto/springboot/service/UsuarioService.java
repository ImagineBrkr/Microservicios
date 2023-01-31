package com.proyecto.springboot.service;


import com.proyecto.springboot.dto.UsuarioRegistroDTO;
import com.proyecto.springboot.model.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UsuarioService extends UserDetailsService {

    public Usuario save(UsuarioRegistroDTO registroDTO);

    public Usuario save(Usuario usuario);

    public List<Usuario> listarUsuarios();

    public boolean existsById(Long id);

    public boolean existsByEmail(String email);

    public Usuario findByEmail(String email);

    public Usuario getUsuario(long id);
    
    public void delete(long id);


}
