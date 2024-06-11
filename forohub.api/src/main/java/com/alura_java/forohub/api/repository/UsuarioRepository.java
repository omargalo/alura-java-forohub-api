package com.alura_java.forohub.api.repository;

import com.alura_java.forohub.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
