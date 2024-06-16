package com.alura_java.forohub.api.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class TopicoUpdateDTO {
    @NotBlank
    private String titulo;

    @NotBlank
    private String mensaje;

    @NotBlank
    private String status;

    @NotNull
    private Long cursoId;
}
