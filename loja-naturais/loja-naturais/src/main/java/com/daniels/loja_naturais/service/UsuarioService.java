package com.daniels.loja_naturais.service;

import com.daniels.loja_naturais.dto.UsuarioRequest;
import com.daniels.loja_naturais.dto.UsuarioResponse;
import com.daniels.loja_naturais.entity.Usuario;
import com.daniels.loja_naturais.mapper.UsuarioMapper;
import com.daniels.loja_naturais.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioResponse salvar(UsuarioRequest dto) {
        Usuario usuario = usuarioMapper.toEntity(dto);
        Usuario salvo = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(salvo);
    }

    public List<UsuarioResponse> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UsuarioResponse buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return usuarioMapper.toResponse(usuario);
    }

    public UsuarioResponse atualizar(Long id, UsuarioRequest dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setPerfil(dto.getPerfil());

        Usuario atualizado = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(atualizado);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
