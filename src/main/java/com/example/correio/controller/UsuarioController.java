package com.example.correio.controller;

import com.example.correio.model.Usuario;
import com.example.correio.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista de todos os usuários cadastrados",
            security = @SecurityRequirement(name = "bearerAuth"))
    public List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter usuário por ID", description = "Retorna um usuário com base no ID fornecido",
            security = @SecurityRequirement(name = "bearerAuth"))
    public Usuario obterPorId(@PathVariable Long id) {
        return usuarioService.obterPorId(id);
    }

    @PostMapping("/registrar")
    @Operation(summary = "Registrar um novo usuário", description = "Cria um novo usuário no sistema")
    public Usuario registrar(@Valid @RequestBody Usuario usuario) {
        return usuarioService.salvar(usuario);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um usuário", description = "Atualiza as informações de um usuário existente",
            security = @SecurityRequirement(name = "bearerAuth"))
    public Usuario atualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
        usuario.setId(id);
        return usuarioService.atualizar(usuario);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um usuário", description = "Remove um usuário do sistema",
            security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
