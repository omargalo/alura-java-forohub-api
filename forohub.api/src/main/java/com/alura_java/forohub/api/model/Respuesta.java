package com.alura_java.forohub.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
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
    private Long idRespuesta;

    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean solucion;

    @ManyToOne
    private Topico topico;

    @ManyToOne
    private Usuario autor;
}
