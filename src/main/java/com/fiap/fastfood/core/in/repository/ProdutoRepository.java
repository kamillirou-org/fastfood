package com.fiap.fastfood.core.in.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fiap.fastfood.core.entities.Produto;

@Repository
public interface ProdutoRepository {

    Produto save(Produto cliente);

    List<Produto> findByCategoria(String categoria);

    List<Produto> findAll();

    void delete(Long id);

    Optional<Produto> findById(Long id);
}
