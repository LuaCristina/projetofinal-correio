package com.example.correio.model;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Schema(description = "Representa um pacote")
public class Pacote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do pacote", example = "1")
    private Long id;

    @NotBlank(message = "A descrição é obrigatória")
    @Schema(description = "Descrição do pacote", example = "Livro")
    private String descricao;

    @NotNull(message = "O peso é obrigatório")
    @Schema(description = "Peso do pacote em kg", example = "1.5")
    private Double peso;

    @Schema(description = "Status do pacote", example = "Em trânsito")
    private String status;

    @ManyToOne
    @Schema(description = "Remetente do pacote")
    private Usuario remetente;

    @ManyToOne
    @Schema(description = "Destinatário do pacote")
    private Usuario destinatario;
}
