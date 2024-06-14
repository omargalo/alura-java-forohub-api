package com.alura_java.forohub.api.controller;

import com.alura_java.forohub.api.dto.TopicoRequest;
import com.alura_java.forohub.api.model.Topico;
import com.alura_java.forohub.api.model.Usuario;
import com.alura_java.forohub.api.model.Curso;
import com.alura_java.forohub.api.service.TopicoService;
import com.alura_java.forohub.api.repository.UsuarioRepository;
import com.alura_java.forohub.api.repository.CursoRepository;
import com.alura_java.forohub.api.repository.TopicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/topicos")
public class TopicoController {

    private static final Logger logger = LoggerFactory.getLogger(TopicoController.class);

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<Topico> registrarTopico(@Validated @RequestBody TopicoRequest topicoRequest) {
        logger.debug("Received request to register topic: {}", topicoRequest);

        Optional<Usuario> autorOpt = usuarioRepository.findById(topicoRequest.getAutorId());
        Optional<Curso> cursoOpt = cursoRepository.findById(topicoRequest.getCursoId());

        if (autorOpt.isEmpty()) {
            logger.error("Author not found with ID: {}", topicoRequest.getAutorId());
            return ResponseEntity.badRequest().body(null);
        }

        if (cursoOpt.isEmpty()) {
            logger.error("Course not found with ID: {}", topicoRequest.getCursoId());
            return ResponseEntity.badRequest().body(null);
        }

        Topico topico = new Topico();
        topico.setTitulo(topicoRequest.getTitulo());
        topico.setMensaje(topicoRequest.getMensaje());
        topico.setFechaCreacion(LocalDateTime.now());
        topico.setStatus(topicoRequest.getStatus()); // Set status field
        topico.setAutor(autorOpt.get());
        topico.setCurso(cursoOpt.get());

        // Check for duplicate topic
        boolean exists = topicoService.existsByTituloAndMensaje(topico.getTitulo(), topico.getMensaje());
        if (exists) {
            logger.error("Duplicate topic with title: {} and message: {}", topico.getTitulo(), topico.getMensaje());
            return ResponseEntity.badRequest().body(null);
        }

        Topico nuevoTopico = topicoService.crearTopico(topico);
        logger.debug("Successfully created topic: {}", nuevoTopico);
        return ResponseEntity.ok(nuevoTopico);
    }

    @GetMapping
    public ResponseEntity<Page<Topico>> listarTopicos(
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) String nombreCurso,
            @RequestParam(required = false) Integer anio
    ) {
        Page<Topico> topicos;

        if (nombreCurso != null && anio != null) {
            topicos = topicoRepository.findByCursoNombreAndFechaCreacionYear(nombreCurso, anio, pageable);
        } else if (nombreCurso != null) {
            topicos = topicoRepository.findByCursoNombre(nombreCurso, pageable);
        } else {
            topicos = topicoRepository.findAll(pageable);
        }

        return ResponseEntity.ok(topicos);
    }


}
