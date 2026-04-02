package com.tecnicatura.tp1_2026.controller;

import com.tecnicatura.tp1_2026.model.JwtResponse;
import com.tecnicatura.tp1_2026.model.LoginRequest;
import com.tecnicatura.tp1_2026.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;

@RestController
@RequestMapping("/auth")
@CrossOrigin // Importante: permite que Angular (puerto 4200) se conecte
public class AuthController {

    @Autowired
    private AuthenticationManager authManager; // El "Jefe" que valida contra la DB

    @Autowired
    private JwtProvider jwtProvider; // Nuestra fábrica de tokens

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // 1. Intentar autenticar al usuario con los datos recibidos
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // 2. Si los datos son correctos. Generamos el token
            String token = jwtProvider.generateToken(auth);

            // 3. Devolvemos el token envuelto en un DTO
            return ResponseEntity.ok(new JwtResponse(token));

        } catch (AuthenticationException e) {
            // 4. Si fallan las credenciales (ej: clave mal escrita), devolvemos 401
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuario o contraseña incorrectos");
        }
    }
}
