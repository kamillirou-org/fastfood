package com.fiap.fastfood.core.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PedidoStatus {

    RECEBIDO("RECEBIDO"),
    EM_PREPARACAO("EM_PREPARACAO"),
    PRONTO("PRONTO"),
    FINALIZADO("FINALIZADO");

    private final String status;

    PedidoStatus(String status) {
        this.status = status;
    }

    @JsonCreator
    public static PedidoStatus fromStatus(String status) {
        for (PedidoStatus pedidoStatus : values()) {
            if (pedidoStatus.status.equalsIgnoreCase(status)) {
                return pedidoStatus;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + status);
    }

}
