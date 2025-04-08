package com.fiap.fastfood.core.in.dto;

import java.util.List;

import com.fiap.fastfood.core.entities.Pedido;
import com.fiap.fastfood.core.entities.PedidoProduto;
import com.fiap.fastfood.core.entities.PedidoStatus;
import com.fiap.fastfood.core.entities.Produto;

public class PedidoDTO {

    private Long id;
    private String cliente;
    private Long clienteId;
    private List<Produto> produtos;
    private PedidoStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public PedidoDTO fromEntity(Pedido pedido) {
        this.id = pedido.getId();
        this.status = pedido.getStatus();
        this.produtos = pedido.getPedidoProduto().stream().map(PedidoProduto::getProduto).toList();
        if(pedido.getCliente() != null){
            this.cliente = pedido.getCliente().getNome();
            this.setClienteId(pedido.getCliente().getId());
        } else {
            this.cliente = "Anônimo";
        }

        return this;
    }
}
