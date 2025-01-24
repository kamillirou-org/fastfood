package com.fiap.fastfood.core.application.repository;

import com.fiap.fastfood.core.domain.Pedido;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository {
    Pedido save(Pedido pedido);

    Pedido find(Long id);

    List<Pedido> list();

    List<Pedido> list(String status);
}
