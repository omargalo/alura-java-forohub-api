package com.alura_java.forohub.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRespuesta;

    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean solucion;

    @ManyToOne
    private Topico topico;

    @ManyToOne
    private Usuario autor;

    // Getters y setters
}
