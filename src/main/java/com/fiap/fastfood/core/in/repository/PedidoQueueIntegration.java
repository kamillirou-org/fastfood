package com.fiap.fastfood.core.in.repository;

import org.springframework.transaction.annotation.Transactional;

import com.fiap.fastfood.core.entities.Pedido;
import com.fiap.fastfood.core.entities.PedidoQueue;

public interface PedidoQueueIntegration {
    @Transactional
    PedidoQueue enviaParaFila(Pedido pedido);

    PedidoQueue getItemByPedidoId(Long pedidoId);
}
