package com.proyecto.springboot.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.proyecto.springboot.dto.UsuarioDTO;
import com.proyecto.springboot.model.Usuario;
import com.proyecto.springboot.service.UsuarioService;

@Component
public class Securityhandler implements AuthenticationSuccessHandler {
    @Resource
    UsuarioService usuarioService;
    

    //@Resource(name = "sessionToken")
    //TokenDTO sessionToken;

     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException  {
         HttpSession session = request.getSession();
         session.setAttribute("wasd", "wasd");

         response.sendRedirect("/" );
    }
}
