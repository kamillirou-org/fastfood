package com.fiap.fastfood.adapters.out.integration.queue;

import com.fiap.fastfood.core.application.PedidoQueueIntegration;
import com.fiap.fastfood.core.domain.Pedido;
import com.fiap.fastfood.core.domain.PedidoQueue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class PedidoQueueIntegrationImpl implements PedidoQueueIntegration {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public PedidoQueue enviaParaFila(Pedido pedido) {
        PedidoQueue pedidoQueue = new PedidoQueue();
        pedidoQueue.setPedido(pedido);
        pedidoQueue.setDataCriacao(LocalDateTime.now());

        entityManager.persist(pedidoQueue);
        return pedidoQueue;
    }

    @Override
    public PedidoQueue getItemByPedidoId(Long pedidoId) {
        String jpql = "SELECT p FROM PedidoQueue p WHERE p.pedido.id = :pedidoId";
        TypedQuery<PedidoQueue> query = entityManager.createQuery(jpql, PedidoQueue.class);
        query.setParameter("pedidoId", pedidoId);

        return query.getSingleResult();
    }

}
