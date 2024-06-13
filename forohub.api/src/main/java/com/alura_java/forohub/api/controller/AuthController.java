package com.alura_java.forohub.api.controller;

import com.alura_java.forohub.api.model.Usuario;
import com.alura_java.forohub.api.service.UsuarioService;
import com.alura_java.forohub.api.util.JwtUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        Usuario nuevoUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        var userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(token);
    }
}

@Setter
@Getter
class LoginRequest {
    // Getters and setters
    private String username;
    private String password;

}
