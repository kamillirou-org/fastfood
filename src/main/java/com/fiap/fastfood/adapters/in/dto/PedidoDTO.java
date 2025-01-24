package com.fiap.fastfood.adapters.in.dto;

import com.fiap.fastfood.core.domain.Pedido;
import com.fiap.fastfood.core.domain.PedidoProduto;
import com.fiap.fastfood.core.domain.PedidoStatus;
import com.fiap.fastfood.core.domain.Produto;

import java.util.List;

public class PedidoDTO {

    private Long id;
    private String cliente;
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

    public PedidoDTO fromEntity(Pedido pedido) {
        this.id = pedido.getId();
        this.status = pedido.getStatus();
        this.produtos = pedido.getPedidoProduto().stream().map(PedidoProduto::getProduto).toList();
        this.cliente = pedido.getCliente().getNome();

        return this;
    }
}
