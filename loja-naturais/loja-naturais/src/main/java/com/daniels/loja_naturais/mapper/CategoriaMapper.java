package com.daniels.loja_naturais.mapper;

import com.daniels.loja_naturais.dto.CategoriaRequest;
import com.daniels.loja_naturais.dto.CategoriaResponse;
import com.daniels.loja_naturais.entity.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public Categoria toEntity(CategoriaRequest dto) {
        return Categoria.builder()
                .nome(dto.getNome())
                .build();
    }

    public CategoriaResponse toResponse(Categoria entity) {
        return CategoriaResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .build();
    }
}
