package com.fiap.fastfood.core.in.dto;

import java.util.List;

public class CriarPedidoDTO {

    private String clienteCPF;

    private String clienteNome;

    private String clienteEmail;

    private Boolean isAnonymousClient;

    private List<Long> produtoIds;

    public String getClienteCPF() {
        return clienteCPF;
    }

    public void setClienteCPF(String clienteCPF) {
        this.clienteCPF = clienteCPF;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public String getClienteEmail() {
        return clienteEmail;
    }

    public void setClienteEmail(String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    public Boolean getAnonymousClient() {
        return isAnonymousClient;
    }

    public void setAnonymousClient(Boolean anonymousClient) {
        isAnonymousClient = anonymousClient;
    }

    public List<Long> getProdutoIds() {
        return produtoIds;
    }

    public void setProdutoIds(List<Long> produtoIds) {
        this.produtoIds = produtoIds;
    }
}