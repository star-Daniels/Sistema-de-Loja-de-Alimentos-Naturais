package com.daniels.loja_naturais.repository;

import com.daniels.loja_naturais.entity.PedidoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, Long> {
}