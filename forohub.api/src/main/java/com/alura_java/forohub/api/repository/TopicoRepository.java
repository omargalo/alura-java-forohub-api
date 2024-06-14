package com.alura_java.forohub.api.repository;

import com.alura_java.forohub.api.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
    Page<Topico> findByCursoNombre(String nombreCurso, Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE t.curso.nombre = :nombreCurso AND YEAR(t.fechaCreacion) = :anio")
    Page<Topico> findByCursoNombreAndFechaCreacionYear(@Param("nombreCurso") String nombreCurso, @Param("anio") Integer anio, Pageable pageable);
}
