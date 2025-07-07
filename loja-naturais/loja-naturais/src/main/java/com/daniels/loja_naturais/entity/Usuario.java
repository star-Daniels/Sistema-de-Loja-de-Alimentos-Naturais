package com.daniels.loja_naturais.entity;


import com.daniels.loja_naturais.enums.Perfil;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    @OneToMany(mappedBy = "usuario")
    private List<EnderecoEntrega> enderecos;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;
}