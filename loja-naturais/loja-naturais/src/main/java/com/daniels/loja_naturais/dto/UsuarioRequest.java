package com.daniels.loja_naturais.dto;

import com.daniels.loja_naturais.enums.Perfil;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UsuarioRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

    @NotNull
    private Perfil perfil;
}