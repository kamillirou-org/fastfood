package com.fiap.fastfood.core.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@Setter
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private String nomeCliente;

    private String emailCliente;

    @Enumerated(EnumType.STRING)
    private PedidoStatus status;

    private BigDecimal total;

    @Setter
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PedidoProduto> pedidoProduto;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataPedido;
}
