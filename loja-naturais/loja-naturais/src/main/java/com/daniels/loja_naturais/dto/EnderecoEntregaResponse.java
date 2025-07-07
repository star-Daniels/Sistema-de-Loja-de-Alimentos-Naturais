package com.daniels.loja_naturais.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnderecoEntregaResponse {
    private Long id;
    private String rua;
    private String cidade;
    private String estado;
    private String cep;
    private Long usuarioId;
}
