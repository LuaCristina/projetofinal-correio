package com.example.correio.controller;

import com.example.correio.model.Endereco;
import com.example.correio.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/enderecos")
@Tag(name = "Endereços", description = "Endpoints para gerenciamento de endereços")
@SecurityRequirement(name = "bearerAuth")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    @Operation(summary = "Listar todos os endereços", description = "Retorna uma lista de todos os endereços cadastrados")
    public List<Endereco> listarTodos() {
        return enderecoService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter endereço por ID", description = "Retorna um endereço com base no ID fornecido")
    public Endereco obterPorId(@PathVariable Long id) {
        return enderecoService.obterPorId(id);
    }

    @PostMapping
    @Operation(summary = "Criar um novo endereço", description = "Cadastra um novo endereço no sistema")
    public Endereco criar(@Valid @RequestBody Endereco endereco) {
        return enderecoService.salvar(endereco);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um endereço", description = "Atualiza as informações de um endereço existente")
    public Endereco atualizar(@PathVariable Long id, @Valid @RequestBody Endereco endereco) {
        endereco.setId(id);
        return enderecoService.atualizar(endereco);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um endereço", description = "Remove um endereço do sistema")
    public void deletar(@PathVariable Long id) {
        enderecoService.deletar(id);
    }
}
