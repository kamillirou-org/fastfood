package com.fiap.fastfood.core.application.service;

import com.fiap.fastfood.adapters.in.dto.CriarPedidoDTO;
import com.fiap.fastfood.adapters.in.dto.PedidoDTO;
import com.fiap.fastfood.core.domain.Pedido;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PedidoService {
    Pedido criarPedido(CriarPedidoDTO pedidoDTO);

    PedidoDTO getPedido(Long id);

    List<PedidoDTO> getPedidos(String status);

    Pedido updateStatus(Long id, String status);
}
