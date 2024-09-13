package com.example.correio.controller;

import com.example.correio.model.Pacote;
import com.example.correio.service.PacoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pacotes")
public class PacoteController {

    @Autowired
    private PacoteService pacoteService;

    @GetMapping
    public List<Pacote> listarTodos() {
        return pacoteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pacote> obterPorId(@PathVariable Long id) {
        return pacoteService.obterPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pacote criar(@RequestBody Pacote pacote) {
        return pacoteService.salvar(pacote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pacote> atualizar(@PathVariable Long id, @RequestBody Pacote pacote) {
        if (!pacoteService.obterPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pacote.setId(id);
        Pacote pacoteAtualizado = pacoteService.salvar(pacote);
        return ResponseEntity.ok(pacoteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!pacoteService.obterPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pacoteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
