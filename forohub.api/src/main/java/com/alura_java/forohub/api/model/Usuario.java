package com.alura_java.forohub.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Long idUsuario;

    @NotBlank
    private String nombre;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @ManyToMany
    @JoinTable(
            name = "UsuarioPerfil",
            joinColumns = @JoinColumn(name = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idPerfil")
    )
    private List<Perfil> perfiles;
}
