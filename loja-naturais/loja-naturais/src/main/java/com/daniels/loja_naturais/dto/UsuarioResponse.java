package com.daniels.loja_naturais.dto;

import com.daniels.loja_naturais.enums.Perfil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponse {
    private Long id;
    private String nome;
    private String email;
    private Perfil perfil;
}
