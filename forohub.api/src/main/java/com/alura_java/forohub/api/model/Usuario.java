package com.alura_java.forohub.api.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombre;
    private String email;
    private String password;

    @ManyToMany
    @JoinTable(
            name = "UsuarioPerfil",
            joinColumns = @JoinColumn(name = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idPerfil")
    )
    private List<Perfil> perfiles;

    // Getters y setters
}
