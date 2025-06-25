package com.daniels.loja_naturais.repository;

import com.daniels.loja_naturais.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}