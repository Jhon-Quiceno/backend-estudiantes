package com.backend.estudiantes.controller;

import com.backend.estudiantes.dto.LoginRequest;
import com.backend.estudiantes.model.Usuario;
import com.backend.estudiantes.service.AuthService;
import com.backend.estudiantes.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    //Endpoint para login
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            Usuario usuario = authService.authenticate(request.email(), request.password());

            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("rol", usuario.getRol().name());
            extraClaims.put("nombre", usuario.getNombre());
            extraClaims.put("email", usuario.getEmail());

            String jwtToken = jwtService.generateToken(extraClaims, usuario);

            return ResponseEntity.ok(Map.of(
                    "message", "Login exitoso!",
                    "token", jwtToken,
                    "expiresIn", jwtService.getJwtExpirationMs(),
                    "data", Map.of(
                            "email", usuario.getEmail(),
                            "rol", usuario.getRol().name()
                    )
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "Error", "Credenciales incorrectas"
            ));
        }
    }

}
