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
    @Column(name = "id_respuesta")
    private Long idRespuesta;

    private String mensaje;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    private Boolean solucion;

    @ManyToOne
    @JoinColumn(name = "topico")
    private Topico topico;

    @ManyToOne
    @JoinColumn(name = "autor")
    private Usuario autor;
}
