package com.proyecto.springboot.service;

import com.proyecto.springboot.dto.UsuarioRegistroDTO;
import com.proyecto.springboot.model.Cliente;
import com.proyecto.springboot.model.Rol;
import com.proyecto.springboot.model.Usuario;
import com.proyecto.springboot.repository.RolRepository;
import com.proyecto.springboot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired(required = true)
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Usuario save(UsuarioRegistroDTO registroDTO) {
        Usuario usuario = new Usuario(registroDTO.getNombre(),
                registroDTO.getApellido(),registroDTO.getEmail(),
                passwordEncoder.encode(registroDTO.getPassword()),
                Arrays.asList(rolRepository.findByNombre("ROLE_ADMIN")));

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario save(Usuario usuario) {
        String password = usuario.getPassword();
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setRoles(Arrays.asList(rolRepository.findByNombre("ROLE_VENDEDOR")));
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Usuario> usuariosEstan = new ArrayList<Usuario>();
        for (int i=0;i<usuarios.size();i++) {
            if (!usuarios.get(i).isDeleted()) {
                usuariosEstan.add(usuarios.get(i));
            }
            
          }
        return usuariosEstan;
    }

    @Override
    public boolean existsById(Long id) {
        return usuarioRepository.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {

        Usuario usuario = usuarioRepository.findByEmail(email);

        if(usuario == null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public void delete(long id) {
        Usuario usuario = usuarioRepository.findByid(id);
        usuario.setDeleted(Boolean.TRUE);
        usuarioRepository.save(usuario);
    }
    
    
    @Override
    public Usuario getUsuario(long id) {
        return usuarioRepository.findByid(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuario o password invalidos");
        }

        return new User(usuario.getEmail(),usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
    }


    public Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }

}
