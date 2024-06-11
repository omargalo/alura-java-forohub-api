package com.alura_java.forohub.api.repository;

import com.alura_java.forohub.api.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
