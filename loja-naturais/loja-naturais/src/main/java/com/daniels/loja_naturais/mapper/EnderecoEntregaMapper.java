package com.daniels.loja_naturais.mapper;

import com.daniels.loja_naturais.dto.EnderecoEntregaRequest;
import com.daniels.loja_naturais.dto.EnderecoEntregaResponse;
import com.daniels.loja_naturais.entity.EnderecoEntrega;
import com.daniels.loja_naturais.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class EnderecoEntregaMapper {

    public EnderecoEntrega toEntity(EnderecoEntregaRequest dto, Usuario usuario) {
        return EnderecoEntrega.builder()
                .rua(dto.getRua())
                .cidade(dto.getCidade())
                .estado(dto.getEstado())
                .cep(dto.getCep())
                .usuario(usuario)
                .build();
    }

    public EnderecoEntregaResponse toResponse(EnderecoEntrega entity) {
        return EnderecoEntregaResponse.builder()
                .id(entity.getId())
                .rua(entity.getRua())
                .cidade(entity.getCidade())
                .estado(entity.getEstado())
                .cep(entity.getCep())
                .usuarioId(entity.getUsuario().getId())
                .build();
    }
}
