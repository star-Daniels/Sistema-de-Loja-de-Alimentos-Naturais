package com.daniels.loja_naturais.mapper;

import com.daniels.loja_naturais.dto.ProdutoRequest;
import com.daniels.loja_naturais.dto.ProdutoResponse;
import com.daniels.loja_naturais.entity.Categoria;
import com.daniels.loja_naturais.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public Produto toEntity(ProdutoRequest dto, Categoria categoria) {
        return Produto.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .preco(dto.getPreco())
                .estoque(dto.getEstoque())
                .imagemUrl(dto.getImagemUrl())
                .categoria(categoria)
                .build();
    }

    public ProdutoResponse toResponse(Produto entity) {
        return ProdutoResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .preco(entity.getPreco())
                .estoque(entity.getEstoque())
                .imagemUrl(entity.getImagemUrl())
                .categoriaNome(entity.getCategoria().getNome())
                .build();
    }
}
