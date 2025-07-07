package com.daniels.loja_naturais.repository;

import com.daniels.loja_naturais.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}