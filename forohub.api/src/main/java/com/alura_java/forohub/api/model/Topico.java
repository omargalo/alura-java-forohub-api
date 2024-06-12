package com.alura_java.forohub.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTopico;

    @NotBlank
    private String titulo;

    @NotBlank
    private String mensaje;

    @NotNull
    private LocalDateTime fechaCreacion;

    @NotBlank
    private String status;

    @ManyToOne
    @NotNull
    private Usuario autor;

    @ManyToOne
    @NotNull
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    private List<Respuesta> respuestas;
}
