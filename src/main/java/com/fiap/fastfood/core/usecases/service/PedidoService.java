package com.fiap.fastfood.core.usecases.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fiap.fastfood.core.entities.Pedido;
import com.fiap.fastfood.core.in.dto.CriarPedidoDTO;
import com.fiap.fastfood.core.in.dto.PedidoDTO;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface PedidoService {

    Pedido criarPedido(CriarPedidoDTO pedidoDTO);

    PedidoDTO getPedido(Long id);

    List<PedidoDTO> getPedidos(String status);

    Pedido updateStatus(Long id, String status);

    @Transactional
    Pedido pagamentoConfirmadoListener(Long idPedido);

    List<PedidoDTO> getPedidosOrdered();
}
