package com.fiap.fastfood.core.infra.persistence;

import java.util.List;

import com.fiap.fastfood.core.entities.PedidoStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.fastfood.core.entities.Pedido;
import com.fiap.fastfood.core.entities.ProductCategory;
import com.fiap.fastfood.core.in.repository.PedidoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

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

    @Override
    public List<Pedido> listOrdered() {
        return entityManager.createQuery(
                """ 
                    SELECT p FROM Pedido p
                        WHERE p.status <> :status
                        ORDER BY
                        CASE p.status
                            WHEN com.fiap.fastfood.core.entities.PedidoStatus.PRONTO THEN 1
                            WHEN com.fiap.fastfood.core.entities.PedidoStatus.EM_PREPARACAO THEN 2
                            WHEN com.fiap.fastfood.core.entities.PedidoStatus.RECEBIDO THEN 3
                        ELSE 4
                        END,
                        p.dataPedido asc
                    """
                , Pedido.class).setParameter("status", PedidoStatus.FINALIZADO).getResultList();
    }
}
