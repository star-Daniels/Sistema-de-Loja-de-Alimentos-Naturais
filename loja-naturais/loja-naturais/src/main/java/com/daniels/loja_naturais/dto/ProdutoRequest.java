package com.daniels.loja_naturais.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoRequest {

    @NotBlank
    private String nome;

    private String descricao;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal preco;

    @Min(0)
    private Integer estoque;

    private String imagemUrl;

    @NotNull
    private Long categoriaId;
}