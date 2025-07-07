package com.daniels.loja_naturais.mapper;

import com.daniels.loja_naturais.dto.PedidoResponse;
import com.daniels.loja_naturais.dto.PedidoItemResponse;
import com.daniels.loja_naturais.entity.Pedido;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoMapper {

    public PedidoResponse toResponse(Pedido pedido) {
        List<PedidoItemResponse> itens = pedido.getItens().stream()
                .map(item -> PedidoItemResponse.builder()
                        .produtoId(item.getProduto().getId())
                        .nomeProduto(item.getProduto().getNome())
                        .quantidade(item.getQuantidade())
                        .precoUnitario(item.getPreco())
                        .build())
                .collect(Collectors.toList());

        return PedidoResponse.builder()
                .id(pedido.getId())
                .data(pedido.getData())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .usuarioId(pedido.getUsuario().getId())
                .itens(itens)
                .build();
    }
}
