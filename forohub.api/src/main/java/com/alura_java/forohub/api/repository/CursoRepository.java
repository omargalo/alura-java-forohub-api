package com.alura_java.forohub.api.repository;

import com.alura_java.forohub.api.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
