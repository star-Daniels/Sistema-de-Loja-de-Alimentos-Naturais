package com.daniels.loja_naturais.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class PedidoRequest {

    @NotNull
    private Long usuarioId;

    @NotNull
    private List<@Valid PedidoItemRequest> itens;

    @NotNull
    private Long enderecoEntregaId;
}