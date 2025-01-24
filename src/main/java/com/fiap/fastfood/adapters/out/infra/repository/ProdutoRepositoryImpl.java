package com.fiap.fastfood.adapters.out.infra.repository;

import com.fiap.fastfood.core.application.repository.ProdutoRepository;
import com.fiap.fastfood.core.domain.ProductCategory;
import com.fiap.fastfood.core.domain.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Produto save(Produto produto) {
        if (produto.getId() == null) {
            entityManager.persist(produto);
        } else {
            entityManager.merge(produto);
        }

        return produto;
    }

    @Override
    public List<Produto> findByCategoria(String categoria) {
        String jpql = "SELECT p FROM Produto p WHERE p.categoria = :categoria";
        TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);
        query.setParameter("categoria", ProductCategory.valueOf(categoria));

        return query.getResultList();
    }

    @Override
    public List<Produto> findAll() {
        return entityManager.createQuery("select p from Produto p", Produto.class).getResultList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Produto produto = entityManager.find(Produto.class, id);

        if (produto != null) {
            entityManager.remove(produto);
        } else {
            throw new IllegalArgumentException("Entity with ID " + id + " not found.");
        }
    }

    @Override
    public Optional<Produto> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Produto.class, id));
    }

}
