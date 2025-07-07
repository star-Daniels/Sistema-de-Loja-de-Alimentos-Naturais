package com.daniels.loja_naturais.service;

import com.daniels.loja_naturais.dto.ProdutoRequest;
import com.daniels.loja_naturais.dto.ProdutoResponse;
import com.daniels.loja_naturais.entity.Categoria;
import com.daniels.loja_naturais.entity.Produto;
import com.daniels.loja_naturais.mapper.ProdutoMapper;
import com.daniels.loja_naturais.repository.CategoriaRepository;
import com.daniels.loja_naturais.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoResponse salvar(ProdutoRequest dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Produto produto = produtoMapper.toEntity(dto, categoria);
        Produto produtoSalvo = produtoRepository.save(produto);

        return produtoMapper.toResponse(produtoSalvo);
    }

    public List<ProdutoResponse> listarTodos() {
        return produtoRepository.findAll().stream()
                .map(produtoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProdutoResponse buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return produtoMapper.toResponse(produto);
    }

    public ProdutoResponse atualizar(Long id, ProdutoRequest dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setEstoque(dto.getEstoque());
        produto.setImagemUrl(dto.getImagemUrl());
        produto.setCategoria(categoria);

        Produto produtoAtualizado = produtoRepository.save(produto);
        return produtoMapper.toResponse(produtoAtualizado);
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }
}
