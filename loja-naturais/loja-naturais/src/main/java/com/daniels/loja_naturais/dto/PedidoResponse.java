package com.daniels.loja_naturais.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PedidoResponse {
    private Long id;
    private LocalDateTime data;
    private BigDecimal total;
    private String status;
    private Long usuarioId;
    private List<PedidoItemResponse> itens;
}
