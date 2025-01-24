package com.fiap.fastfood.adapters.in.dto;

import java.util.List;

public class CriarPedidoDTO {

    private String clienteCPF;
    private List<Long> produtoIds;

    public String getClienteCPF() {
        return clienteCPF;
    }

    public void setClienteCPF(String clienteCPF) {
        this.clienteCPF = clienteCPF;
    }

    public List<Long> getProdutoIds() {
        return produtoIds;
    }

    public void setProdutoIds(List<Long> produtoIds) {
        this.produtoIds = produtoIds;
    }
}
