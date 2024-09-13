package com.example.correio.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Schema(description = "Representa um usuário do sistema")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do usuário", example = "1")
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Schema(description = "Nome do usuário", example = "João Silva")
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O email deve ser válido")
    @Column(unique = true)
    @Schema(description = "Email do usuário", example = "joao.silva@example.com")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Schema(description = "Senha do usuário", example = "senhaSegura123")
    private String senha;

    @OneToOne(cascade = CascadeType.ALL)
    @Schema(description = "Endereço do usuário")
    private Endereco endereco;
}
