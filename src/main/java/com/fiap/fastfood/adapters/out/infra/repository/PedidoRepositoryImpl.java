package com.fiap.fastfood.adapters.out.infra.repository;

import com.fiap.fastfood.core.application.repository.PedidoRepository;
import com.fiap.fastfood.core.domain.Pedido;
import com.fiap.fastfood.core.domain.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PedidoRepositoryImpl implements PedidoRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public PedidoRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Pedido save(Pedido pedido) {
        if (pedido.getId() == null) {
            entityManager.persist(pedido);
        } else {
            entityManager.merge(pedido);
        }
        return pedido;
    }

    @Override
    public Pedido find(Long id) {
        return entityManager.find(Pedido.class, id);
    }

    @Override
    public List<Pedido> list() {
        return entityManager.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
    }

    @Override
    public List<Pedido> list(String status) {
        String jpql = "SELECT p FROM Pedido p WHERE p.status = :status";
        TypedQuery<Pedido> query = entityManager.createQuery(jpql, Pedido.class);
        query.setParameter("status", ProductCategory.valueOf(status));

        return query.getResultList();
    }
}
