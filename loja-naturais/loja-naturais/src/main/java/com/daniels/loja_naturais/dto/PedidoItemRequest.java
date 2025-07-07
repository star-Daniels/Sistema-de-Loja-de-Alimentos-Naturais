package com.daniels.loja_naturais.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PedidoItemRequest {

    @NotNull
    private Long produtoId;

    @Min(1)
    private Integer quantidade;
}