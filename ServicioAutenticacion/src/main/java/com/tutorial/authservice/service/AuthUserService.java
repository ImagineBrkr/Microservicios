package com.tutorial.authservice.service;

import com.tutorial.authservice.dto.AuthUserDto;
import com.tutorial.authservice.dto.NewUserDto;
import com.tutorial.authservice.dto.RequestDto;
import com.tutorial.authservice.dto.TokenDto;
import com.tutorial.authservice.entity.Usuario;
import com.tutorial.authservice.repository.AuthUserRepository;
import com.tutorial.authservice.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserService {

    @Autowired
    AuthUserRepository authUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

//    public Usuario save(NewUserDto dto) {
//        Optional<Usuario> user = authUserRepository.findByUserName(dto.getUserName());
//        if(user.isPresent())
//            return null;
//        String password = passwordEncoder.encode(dto.getPassword());
//        Usuario usuario = Usuario.builder()
//                .userName(dto.getUserName())
//                .password(password)
//                .role(dto.getRole())
//                .build();
//        return authUserRepository.save(usuario);
//    }

    public TokenDto login(AuthUserDto dto) {
        Usuario user = authUserRepository.findByEmail(dto.getUsername());
        if(user.isDeleted())
            return null;
        if(passwordEncoder.matches(dto.getPassword(), user.getPassword()))
            return new TokenDto(jwtProvider.createToken(user));
        return null;
    }

    public TokenDto validate(String token, RequestDto dto) {
        if(!jwtProvider.validate(token, dto))
            return null;
        return new TokenDto(token);
    }
}
