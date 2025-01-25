package com.fiap.fastfood.core.application;

import com.fiap.fastfood.core.domain.Pedido;
import com.fiap.fastfood.core.domain.PedidoQueue;
import org.springframework.transaction.annotation.Transactional;

public interface PedidoQueueIntegration {
    @Transactional
    PedidoQueue enviaParaFila(Pedido pedido);

    PedidoQueue getItemByPedidoId(Long pedidoId);
}
