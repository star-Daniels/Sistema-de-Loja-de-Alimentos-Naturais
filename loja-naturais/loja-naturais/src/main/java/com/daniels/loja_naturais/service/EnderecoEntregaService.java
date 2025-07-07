package com.daniels.loja_naturais.service;

import com.daniels.loja_naturais.dto.EnderecoEntregaRequest;
import com.daniels.loja_naturais.dto.EnderecoEntregaResponse;
import com.daniels.loja_naturais.entity.EnderecoEntrega;
import com.daniels.loja_naturais.entity.Usuario;
import com.daniels.loja_naturais.mapper.EnderecoEntregaMapper;
import com.daniels.loja_naturais.repository.EnderecoEntregaRepository;
import com.daniels.loja_naturais.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnderecoEntregaService {

    private final EnderecoEntregaRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EnderecoEntregaMapper enderecoMapper;

    public EnderecoEntregaResponse salvar(EnderecoEntregaRequest dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        EnderecoEntrega endereco = enderecoMapper.toEntity(dto, usuario);
        EnderecoEntrega salvo = enderecoRepository.save(endereco);
        return enderecoMapper.toResponse(salvo);
    }

    public List<EnderecoEntregaResponse> listarTodos() {
        return enderecoRepository.findAll().stream()
                .map(enderecoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public EnderecoEntregaResponse buscarPorId(Long id) {
        EnderecoEntrega endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        return enderecoMapper.toResponse(endereco);
    }

    public EnderecoEntregaResponse atualizar(Long id, EnderecoEntregaRequest dto) {
        EnderecoEntrega endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        endereco.setRua(dto.getRua());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setCep(dto.getCep());
        endereco.setUsuario(usuario);

        EnderecoEntrega atualizado = enderecoRepository.save(endereco);
        return enderecoMapper.toResponse(atualizado);
    }

    public void deletar(Long id) {
        enderecoRepository.deleteById(id);
    }
}
