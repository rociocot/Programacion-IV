package com.tecnicatura.tp1_2026.service;

import com.tecnicatura.tp1_2026.model.Usuario;
import com.tecnicatura.tp1_2026.repository.UsuarioRepository;
import com.tecnicatura.tp1_2026.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String username, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getPassword().equals(password)) {
                return jwtUtil.generateToken(username);
            }
        }
        return null;
    }
}