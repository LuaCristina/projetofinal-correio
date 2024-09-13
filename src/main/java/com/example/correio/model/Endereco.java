package com.example.correio.model;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Schema(description = "Representa um endereço")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do endereço", example = "1")
    private Long id;

    @NotBlank(message = "A rua é obrigatória")
    @Schema(description = "Rua do endereço", example = "Rua das Flores")
    private String rua;

    @NotBlank(message = "O número é obrigatório")
    @Schema(description = "Número do endereço", example = "123")
    private String numero;

    @NotBlank(message = "O bairro é obrigatório")
    @Schema(description = "Bairro do endereço", example = "Centro")
    private String bairro;

    @NotBlank(message = "A cidade é obrigatória")
    @Schema(description = "Cidade do endereço", example = "São Paulo")
    private String cidade;

    @NotBlank(message = "O estado é obrigatório")
    @Schema(description = "Estado do endereço", example = "SP")
    private String estado;

    @NotBlank(message = "O CEP é obrigatório")
    @Schema(description = "CEP do endereço", example = "01000-000")
    private String cep;
}
