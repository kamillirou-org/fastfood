package com.fiap.fastfood.core.entities;

import com.fiap.fastfood.core.infra.persistence.converter.CryptoConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String meioPagamento;

    private String status;

    private BigDecimal valor;

    private Long pedidoId;

    private String webhook;

    @Convert(converter = CryptoConverter.class)
    private String pagamentoAuth;
}
