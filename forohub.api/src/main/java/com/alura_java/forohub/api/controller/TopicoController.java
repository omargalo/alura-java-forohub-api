package com.alura_java.forohub.api.controller;

import com.alura_java.forohub.api.model.Topico;
import com.alura_java.forohub.api.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<Topico> crearTopico(@Validated @RequestBody Topico topico) {
        Topico nuevoTopico = topicoService.crearTopico(topico);
        return ResponseEntity.ok(nuevoTopico);
    }

    @GetMapping
    public List<Topico> obtenerTodosLosTopicos() {
        return topicoService.obtenerTodosLosTopicos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> obtenerTopicoPorId(@PathVariable Long id) {
        Optional<Topico> topico = topicoService.obtenerTopicoPorId(id);
        return topico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> actualizarTopico(@PathVariable Long id, @Validated @RequestBody Topico topico) {
        if (topicoService.obtenerTopicoPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        topico.setIdTopico(id);
        Topico topicoActualizado = topicoService.actualizarTopico(topico);
        return ResponseEntity.ok(topicoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        if (topicoService.obtenerTopicoPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
