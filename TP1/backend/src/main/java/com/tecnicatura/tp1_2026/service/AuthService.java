package com.tecnicatura.tp1_2026.service;

import com.tecnicatura.tp1_2026.model.Usuario;
import com.tecnicatura.tp1_2026.repository.UsuarioRepository;
import com.tecnicatura.tp1_2026.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Optional;
import java.util.Collections;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtProvider jwtUtil;

    public String login(String username, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getPassword().equals(password)) {
//                return jwtUtil.generateToken(username); No toma username por ser string, entonces:
                //ACA CREO EL OBTEJO Authentication
                Authentication auth = new UsernamePasswordAuthenticationToken(
                        usuario.getUsername(),
                        null,
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                );

                // le pasamos el objeto 'auth' y no el String 'username'
                return jwtUtil.generateToken(auth);
            }
        }
        return null;
    }
}