package com.daniels.loja_naturais.mapper;

import com.daniels.loja_naturais.dto.PedidoItemRequest;
import com.daniels.loja_naturais.dto.PedidoItemResponse;
import com.daniels.loja_naturais.entity.PedidoItem;
import com.daniels.loja_naturais.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class PedidoItemMapper {

    public PedidoItem toEntity(PedidoItemRequest dto, Produto produto) {
        return PedidoItem.builder()
                .produto(produto)
                .quantidade(dto.getQuantidade())
                .preco(produto.getPreco())
                .build();
    }

    public PedidoItemResponse toResponse(PedidoItem entity) {
        return PedidoItemResponse.builder()
                .produtoId(entity.getProduto().getId())
                .nomeProduto(entity.getProduto().getNome())
                .quantidade(entity.getQuantidade())
                .precoUnitario(entity.getPreco())
                .build();
    }
}
