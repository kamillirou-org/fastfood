package com.fiap.fastfood.core.in.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fiap.fastfood.core.entities.Pedido;

@Repository
public interface PedidoRepository {
    Pedido save(Pedido pedido);

    Pedido find(Long id);

    List<Pedido> list();

    List<Pedido> list(String status);

    List<Pedido> listOrdered();
}
