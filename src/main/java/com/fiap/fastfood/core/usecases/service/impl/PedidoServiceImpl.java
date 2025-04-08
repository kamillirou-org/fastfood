package com.fiap.fastfood.core.usecases.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fiap.fastfood.core.entities.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.fiap.fastfood.core.in.dto.CriarPedidoDTO;
import com.fiap.fastfood.core.in.dto.PedidoDTO;
import com.fiap.fastfood.core.in.repository.ClienteRepository;
import com.fiap.fastfood.core.in.repository.PedidoQueueIntegration;
import com.fiap.fastfood.core.in.repository.PedidoRepository;
import com.fiap.fastfood.core.in.repository.ProdutoRepository;
import com.fiap.fastfood.core.usecases.service.PedidoService;

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
        String cpfCliente = pedidoDTO.getClienteCPF();
        if(cpfCliente != null) {
            Cliente cliente = clienteRepository.findByCpf(cpfCliente);

            if(cliente == null) {
                cliente = new Cliente(cpfCliente, pedidoDTO.getClienteNome(), pedidoDTO.getClienteEmail());
                clienteRepository.save(cliente);
                pedido.setCliente(cliente);
            }
        }
        pedido.setNomeCliente(pedidoDTO.getClienteNome());
        pedido.setEmailCliente(pedidoDTO.getClienteEmail());
        pedido.setStatus(PedidoStatus.ESPERANDO_PAGAMENTO);

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
        List<Pedido> pedidos;
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

    @Transactional
    @Override
    public Pedido pagamentoConfirmadoListener(Long idPedido) {
        Pedido pedido = pedidoRepository.find(idPedido);
        pedido.setStatus(PedidoStatus.RECEBIDO);

        return pedidoRepository.save(pedido);
    }

    @Override
    public List<PedidoDTO> getPedidosOrdered() {
        return pedidoRepository.listOrdered().stream().map(pedido -> new PedidoDTO().fromEntity(pedido)).collect(Collectors.toList());
    }
}
