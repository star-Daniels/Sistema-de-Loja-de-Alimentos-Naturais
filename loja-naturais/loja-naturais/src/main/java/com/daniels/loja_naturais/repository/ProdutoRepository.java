package com.daniels.loja_naturais.repository;

import com.daniels.loja_naturais.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}