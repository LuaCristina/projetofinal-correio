package com.example.correio.controller;

import com.example.correio.model.Pacote;
import com.example.correio.service.PacoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pacotes")
@Tag(name = "Pacotes", description = "Endpoints para gerenciamento de pacotes")
@SecurityRequirement(name = "bearerAuth")
public class PacoteController {

    @Autowired
    private PacoteService pacoteService;

    @GetMapping
    @Operation(summary = "Listar todos os pacotes", description = "Retorna uma lista de todos os pacotes cadastrados")
    public List<Pacote> listarTodos() {
        return pacoteService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter pacote por ID", description = "Retorna um pacote com base no ID fornecido")
    public Pacote obterPorId(@PathVariable Long id) {
        return pacoteService.obterPorId(id);
    }

    @PostMapping
    @Operation(summary = "Registrar um novo pacote", description = "Cadastra um novo pacote no sistema")
    public Pacote criar(@Valid @RequestBody Pacote pacote) {
        return pacoteService.salvar(pacote);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um pacote", description = "Atualiza as informações de um pacote existente")
    public Pacote atualizar(@PathVariable Long id, @Valid @RequestBody Pacote pacote) {
        pacote.setId(id);
        return pacoteService.atualizar(pacote);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um pacote", description = "Remove um pacote do sistema")
    public void deletar(@PathVariable Long id) {
        pacoteService.deletar(id);
    }
}
