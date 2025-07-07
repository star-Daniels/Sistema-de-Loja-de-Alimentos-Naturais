package com.daniels.loja_naturais.service;

import com.daniels.loja_naturais.dto.PedidoRequest;
import com.daniels.loja_naturais.dto.PedidoResponse;
import com.daniels.loja_naturais.entity.*;
import com.daniels.loja_naturais.enums.Status;
import com.daniels.loja_naturais.mapper.PedidoMapper;
import com.daniels.loja_naturais.mapper.PedidoItemMapper;
import com.daniels.loja_naturais.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EnderecoEntregaRepository enderecoRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoMapper pedidoMapper;
    private final PedidoItemMapper pedidoItemMapper;

    public PedidoResponse salvar(PedidoRequest dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        EnderecoEntrega endereco = enderecoRepository.findById(dto.getEnderecoEntregaId())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        List<PedidoItem> itens = dto.getItens().stream().map(itemReq -> {
            Produto produto = produtoRepository.findById(itemReq.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + itemReq.getProdutoId()));
            return pedidoItemMapper.toEntity(itemReq, produto);
        }).collect(Collectors.toList());

        BigDecimal total = itens.stream()
                .map(item -> item.getPreco().multiply(new BigDecimal(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Pedido pedido = Pedido.builder()
                .usuario(usuario)
                .enderecoEntrega(endereco)
                .itens(itens)
                .data(LocalDateTime.now())
                .status(Status.PENDENTE)
                .total(total)
                .build();

        Pedido salvo = pedidoRepository.save(pedido);

        return pedidoMapper.toResponse(salvo);
    }

    public List<PedidoResponse> listarTodos() {
        return pedidoRepository.findAll().stream()
                .map(pedidoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public PedidoResponse buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return pedidoMapper.toResponse(pedido);
    }

    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }
}
