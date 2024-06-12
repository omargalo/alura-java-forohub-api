package com.alura_java.forohub.api.controller;

import com.alura_java.forohub.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        var userDetails = userDetailsService.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetails.getUsername());
    }
}
