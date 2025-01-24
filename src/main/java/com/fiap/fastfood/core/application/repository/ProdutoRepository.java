package com.fiap.fastfood.core.application.repository;

import com.fiap.fastfood.core.domain.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository {

    Produto save(Produto cliente);

    List<Produto> findByCategoria(String categoria);

    List<Produto> findAll();

    void delete(Long id);

    Optional<Produto> findById(Long id);
}
