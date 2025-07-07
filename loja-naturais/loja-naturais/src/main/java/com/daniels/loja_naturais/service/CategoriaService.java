package com.daniels.loja_naturais.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.daniels.loja_naturais.dto.CategoriaRequest;
import com.daniels.loja_naturais.dto.CategoriaResponse;
import com.daniels.loja_naturais.entity.Categoria;
import com.daniels.loja_naturais.mapper.CategoriaMapper;
import com.daniels.loja_naturais.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaResponse salvar(CategoriaRequest dto) {
        Categoria categoria = categoriaMapper.toEntity(dto);
        Categoria salvo = categoriaRepository.save(categoria);
        return categoriaMapper.toResponse(salvo);
    }

    public List<CategoriaResponse> listarTodos() {
        return categoriaRepository.findAll().stream()
                .map(categoriaMapper::toResponse)
                .collect(Collectors.toList());
    }

    public CategoriaResponse buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return categoriaMapper.toResponse(categoria);
    }

     public CategoriaResponse atualizar(Long id, CategoriaRequest dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        categoria.setNome(dto.getNome());
        Categoria atualizado = categoriaRepository.save(categoria);
        return categoriaMapper.toResponse(atualizado);
    }

     public void deletar(Long id) {
        categoriaRepository.deleteById(id);
    }
}
