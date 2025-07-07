package com.daniels.loja_naturais.mapper;

import com.daniels.loja_naturais.dto.UsuarioRequest;
import com.daniels.loja_naturais.dto.UsuarioResponse;
import com.daniels.loja_naturais.entity.Usuario;

public class UsuarioMapper {
    public Usuario toEntity(UsuarioRequest dto){
        return Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .perfil(dto.getPerfil())
                .build();
    }

    public UsuarioResponse toResponse(Usuario entity) {
        return UsuarioResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .perfil(entity.getPerfil())
                .build();
    }
}
