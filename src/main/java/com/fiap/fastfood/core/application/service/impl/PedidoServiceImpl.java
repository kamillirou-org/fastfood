package com.fiap.fastfood.core.application.service.impl;

import com.fiap.fastfood.adapters.in.dto.CriarPedidoDTO;
import com.fiap.fastfood.adapters.in.dto.PedidoDTO;
import com.fiap.fastfood.core.application.PedidoQueueIntegration;
import com.fiap.fastfood.core.application.repository.ClienteRepository;
import com.fiap.fastfood.core.application.repository.PedidoRepository;
import com.fiap.fastfood.core.application.repository.ProdutoRepository;
import com.fiap.fastfood.core.application.service.PedidoService;
import com.fiap.fastfood.core.domain.Pedido;
import com.fiap.fastfood.core.domain.PedidoProduto;
import com.fiap.fastfood.core.domain.PedidoStatus;
import com.fiap.fastfood.core.domain.Produto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final ProdutoRepository produtoRepository;

    private final PedidoRepository pedidoRepository;

    private final ClienteRepository clienteRepository;

    private final PedidoQueueIntegration pedidoQueueIntegration;

    public PedidoServiceImpl(ProdutoRepository produtoRepository, PedidoRepository pedidoRepository, ClienteRepository clienteRepository, PedidoQueueIntegration pedidoQueueIntegration) {
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.pedidoQueueIntegration = pedidoQueueIntegration;
    }

    @Override
    public Pedido criarPedido(@RequestBody CriarPedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        if(pedidoDTO.getClienteCPF() != null){
            pedido.setCliente(clienteRepository.findByCpf(pedidoDTO.getClienteCPF()));
        }
        pedido.setStatus(PedidoStatus.RECEBIDO);
        Set<PedidoProduto> produtos = pedidoDTO.getProdutoIds().stream().map(produtoId -> {
            Produto produto = produtoRepository.findById(produtoId).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

            PedidoProduto pedidoProduto = new PedidoProduto();
            pedidoProduto.setProduto(produto);
            pedidoProduto.setPedido(pedido);
            pedidoProduto.setPreco(produto.getPreco());

            return pedidoProduto;

        }).collect(Collectors.toSet());

        pedido.setPedidoProduto(produtos);
        pedidoRepository.save(pedido);
        pedidoQueueIntegration.enviaParaFila(pedido);

        return pedido;
    }

    @Override
    public PedidoDTO getPedido(Long id) {
        Pedido pedido = pedidoRepository.find(id);

        PedidoDTO pedidoDTO = new PedidoDTO();

        return pedidoDTO.fromEntity(pedido);
    }

    @Override
    public List<PedidoDTO> getPedidos(String status) {
        List<Pedido> pedidos = new ArrayList<>();
        if(status != null) {
            pedidos = this.pedidoRepository.list(status);
        } else {
            pedidos = this.pedidoRepository.list();
        }

        return pedidos.stream().map(pedido -> new PedidoDTO().fromEntity(pedido)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Pedido updateStatus(Long id, String status) {
        Pedido pedido = pedidoRepository.find(id);
        PedidoStatus pedidoStatus = PedidoStatus.valueOf(status);
        pedido.setStatus(pedidoStatus);

        pedidoRepository.save(pedido);

        return pedido;
    }
}
