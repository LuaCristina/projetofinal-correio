package com.example.correio.controller;

import com.example.correio.model.Endereco;
import com.example.correio.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<Endereco> listarTodos() {
        return enderecoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> obterPorId(@PathVariable Long id) {
        return enderecoService.obterPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Endereco criar(@RequestBody Endereco endereco) {
        return enderecoService.salvar(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @RequestBody Endereco endereco) {
        if (!enderecoService.obterPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        endereco.setId(id);
        Endereco enderecoAtualizado = enderecoService.salvar(endereco);
        return ResponseEntity.ok(enderecoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!enderecoService.obterPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        enderecoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
