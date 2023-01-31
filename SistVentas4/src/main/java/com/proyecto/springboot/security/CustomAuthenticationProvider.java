package com.proyecto.springboot.security;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.proyecto.springboot.dto.UsuarioDTO;
import com.proyecto.springboot.feignclient.GatewayClient;
import com.proyecto.springboot.model.Usuario;
import com.proyecto.springboot.service.UsuarioService;
import com.proyecto.springboot.service.UsuarioServiceImpl;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Resource
    UsuarioService usuarioService;
    
    @Autowired
    UsuarioServiceImpl temp;

    @Autowired
    GatewayClient authClient;
    @Autowired
    HttpSession httpSession;
    

    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * <p> The authenticate method to authenticate the request. We will get the username from the Authentication object and will
     * use the custom @userDetailsService service to load the given user.</p>
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        BCryptPasswordEncoder passwordEncoder = passwordEncoder();
        
        // get user details using Spring security user details service
        Usuario user = null;
        try {
            user = usuarioService.findByEmail(name);
        } catch (UsernameNotFoundException exception) {
            throw new BadCredentialsException("invalid login details");
        }
        if (user == null) {
            throw new BadCredentialsException("invalid login details");
        }
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new BadCredentialsException("invalid login details");
        UsuarioDTO usuario = new UsuarioDTO(name, password);
        String token = authClient.login(usuario);
        
        httpSession.setAttribute("token", token);
        UsernamePasswordAuthenticationToken result = UsernamePasswordAuthenticationToken.authenticated(user,
                authentication.getCredentials(), temp.mapearAutoridadesRoles(user.getRoles()));
        result.setDetails(authentication.getDetails());
        return result;
    }


    @Override
    public boolean supports(Class <?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}