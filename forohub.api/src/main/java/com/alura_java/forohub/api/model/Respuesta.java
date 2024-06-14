package com.alura_java.forohub.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_respuesta")
    private Long idRespuesta;

    @NotBlank
    private String mensaje;

    @NotNull
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @NotNull
    private Boolean solucion;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;
}
